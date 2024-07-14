package com.Spotivent.SpotiventBackend.users.service.impl;

import com.Spotivent.SpotiventBackend.referrals.dto.CreateReferralRequestDTO;
import com.Spotivent.SpotiventBackend.referrals.entity.Referrals;
import com.Spotivent.SpotiventBackend.referrals.service.ReferralService;
import com.Spotivent.SpotiventBackend.users.dto.RegisterRequestDTO;
import com.Spotivent.SpotiventBackend.users.dto.RegisterResponseDTO;
import com.Spotivent.SpotiventBackend.users.entity.Users;
import com.Spotivent.SpotiventBackend.users.repository.UserRepository;
import com.Spotivent.SpotiventBackend.users.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ReferralService referralService;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, ReferralService referralService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.referralService = referralService;
    }

    @Override
    public RegisterResponseDTO register(RegisterRequestDTO registerRequestDTO) {
        Users newUser = new Users();
        newUser.setEmail(registerRequestDTO.getEmail());
        newUser.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
        newUser.setUsername(registerRequestDTO.getUsername());
        newUser.setRole(registerRequestDTO.getRole());
        newUser.setReferralCode(UUID.randomUUID().toString().replace("-", "").substring(0,6));
        Users saved = userRepository.save(newUser);

        if (registerRequestDTO.getReferralCode() != "" && registerRequestDTO.getReferralCode() != null) {
            Users referrer = userRepository.findByReferralCode(registerRequestDTO.getReferralCode()).orElseThrow(() -> new RuntimeException("Referral Code Not Found"));
            CreateReferralRequestDTO requestDTO = new CreateReferralRequestDTO();
            requestDTO.setUserId(saved.getId());
            requestDTO.setClaimed(false);
            requestDTO.setReferrerId(referrer.getId());
            referralService.createReferralCode(requestDTO);
        }

        RegisterResponseDTO response = new RegisterResponseDTO();
        response.setId(saved.getId());
        response.setReferralCode(saved.getReferralCode());
        response.setEmail(saved.getEmail());
        response.setRole(saved.getRole());
        response.setAvatar(saved.getAvatar());
        response.setUsername(saved.getUsername());
        return response;
    }

    @Override
    public RegisterResponseDTO getUserById(Long id) {
        Users users = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        Referrals referrals = referralService.getByUsersId(id);
        RegisterResponseDTO response = new RegisterResponseDTO();
        response.setId(users.getId());
        response.setEmail(users.getEmail());
        response.setRole(users.getRole());
        response.setAvatar(users.getAvatar());
        response.setUsername(users.getUsername());
        response.setReferralCode(users.getReferralCode());
        return response;
    }

    @Override
    public Users getDetailUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

}
