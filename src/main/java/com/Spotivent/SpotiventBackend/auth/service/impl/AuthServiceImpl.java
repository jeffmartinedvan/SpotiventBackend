package com.Spotivent.SpotiventBackend.auth.service.impl;

import com.Spotivent.SpotiventBackend.auth.dto.LoginResponseDto;
import com.Spotivent.SpotiventBackend.auth.service.AuthService;
import com.Spotivent.SpotiventBackend.exception.ApplicationException;
import com.Spotivent.SpotiventBackend.points.service.PointService;
import com.Spotivent.SpotiventBackend.users.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {
    private final JwtEncoder jwtEncoder;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final PointService pointService;

    public AuthServiceImpl(JwtEncoder jwtEncoder, PasswordEncoder passwordEncoder, UserRepository userRepository, PointService pointService) {
        this.jwtEncoder = jwtEncoder;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.pointService = pointService;
    }

    public LoginResponseDto generateToken(Authentication authentication) {
        Instant now = Instant.now();
        var userDetails = userRepository.findByEmail(authentication.getName());
        var pointDetails = pointService.getTotalPointsByUserId(userDetails.get().getId());
        if (userDetails.isEmpty()) {
            throw new ApplicationException("User not found");
        }

        String role = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(10, ChronoUnit.HOURS))
                .subject(authentication.getName())
                .claim("role", role)
                .claim("id", userDetails.get().getId())
                .claim("username", userDetails.get().getUsername())
                .claim("point", pointDetails)
                .build();

        var token = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        LoginResponseDto responseDto = new LoginResponseDto();
        responseDto.setToken(token);
        responseDto.setRole(role);
        responseDto.setMessage("User logged in successfully");
        return responseDto;
    }
}
