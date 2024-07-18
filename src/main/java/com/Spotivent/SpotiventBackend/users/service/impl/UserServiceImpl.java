package com.Spotivent.SpotiventBackend.users.service.impl;

import com.Spotivent.SpotiventBackend.exception.ApplicationException;
import com.Spotivent.SpotiventBackend.points.dto.CreatePointRequestDTO;
import com.Spotivent.SpotiventBackend.points.service.PointService;
import com.Spotivent.SpotiventBackend.referrals.dto.CreateReferralRequestDTO;
import com.Spotivent.SpotiventBackend.referrals.service.ReferralService;
import com.Spotivent.SpotiventBackend.users.dto.RegisterRequestDTO;
import com.Spotivent.SpotiventBackend.users.dto.RegisterResponseDTO;
import com.Spotivent.SpotiventBackend.users.entity.Roles;
import com.Spotivent.SpotiventBackend.users.entity.Users;
import com.Spotivent.SpotiventBackend.users.repository.UserRepository;
import com.Spotivent.SpotiventBackend.users.service.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ReferralService referralService;
    private final PointService pointService;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, @Lazy ReferralService referralService, @Lazy PointService pointService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.referralService = referralService;
        this.pointService = pointService;
    }

    @Override
    public RegisterResponseDTO register(RegisterRequestDTO registerRequestDTO) {
        Optional<Users> existingUser = userRepository.findByEmail(registerRequestDTO.getEmail());
        if (existingUser.isPresent()) {
            throw new ApplicationException("User already exist, please log in with this email");
        }

        Users newUser = new Users();
        newUser.setEmail(registerRequestDTO.getEmail());
        newUser.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
        newUser.setUsername(registerRequestDTO.getUsername());
        newUser.setRole(registerRequestDTO.getRole());
        if (registerRequestDTO.getRole() == Roles.USER) {
            newUser.setReferralCode(UUID.randomUUID().toString().replace("-", "").substring(0, 6));
        }
        Users saved = userRepository.save(newUser);

        if (registerRequestDTO.getReferralCode() != "" && registerRequestDTO.getReferralCode() != null) {
            Users referrer = userRepository.findByReferralCode(registerRequestDTO.getReferralCode()).orElseThrow(() -> new ApplicationException("Referral Code Not Found"));

            CreateReferralRequestDTO requestDTO = new CreateReferralRequestDTO();
            requestDTO.setUserId(saved.getId());
            requestDTO.setClaimed(false);
            requestDTO.setReferrerId(referrer.getId());
            referralService.createReferralCode(requestDTO);

            CreatePointRequestDTO pointRequestDTO = new CreatePointRequestDTO();
            pointRequestDTO.setPoint(10000L);
            pointRequestDTO.setUserId(referrer.getId());
            pointRequestDTO.setExpirationDate(Instant.now().plus(90, ChronoUnit.DAYS));
            pointService.createPoints(pointRequestDTO);
        }
        return mapToRegisterResponseDTO(saved);
    }

    @Override
    public RegisterResponseDTO getUserById(Long id) {
        Users users = userRepository.findById(id).orElseThrow(() -> new ApplicationException("User not found"));
        return mapToRegisterResponseDTO(users);
    }

    @Override
    public Users getDetailUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ApplicationException("User not found"));
    }

    @Override
    public Users findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new ApplicationException("User not found"));
    }

    public RegisterResponseDTO mapToRegisterResponseDTO(Users users) {
        RegisterResponseDTO response = new RegisterResponseDTO();
        response.setId(users.getId());
        response.setReferralCode(users.getReferralCode());
        response.setEmail(users.getEmail());
        response.setRole(users.getRole());
        response.setAvatar(users.getAvatar());
        response.setUsername(users.getUsername());
        return response;
    }
}
