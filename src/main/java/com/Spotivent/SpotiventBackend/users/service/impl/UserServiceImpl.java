package com.Spotivent.SpotiventBackend.users.service.impl;

import com.Spotivent.SpotiventBackend.users.dto.RegisterRequestDTO;
import com.Spotivent.SpotiventBackend.users.entity.Users;
import com.Spotivent.SpotiventBackend.users.repository.UserRepository;
import com.Spotivent.SpotiventBackend.users.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Users register(RegisterRequestDTO registerRequestDTO) {
        Users newUser = new Users();
        newUser.setEmail(registerRequestDTO.getEmail());
        var password = passwordEncoder.encode(registerRequestDTO.getPassword());
        newUser.setPassword(password);
        newUser.setUsername(registerRequestDTO.getUsername());
        newUser.setRole(registerRequestDTO.getRole());
        return userRepository.save(newUser);
    }
}
