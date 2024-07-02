package com.Spotivent.SpotiventBackend.users.service.impl;

import com.Spotivent.SpotiventBackend.users.dto.RegisterRequestDTO;
import com.Spotivent.SpotiventBackend.users.entity.Users;
import com.Spotivent.SpotiventBackend.users.repository.UserRepository;
import com.Spotivent.SpotiventBackend.users.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Users register(RegisterRequestDTO registerRequestDTO) {
        Users newUser = new Users();
        newUser.setEmail(registerRequestDTO.getEmail());
        newUser.setPassword(registerRequestDTO.getPassword());
        newUser.setUsername(registerRequestDTO.getUsername());
        newUser.setRole(registerRequestDTO.getRole());
        return userRepository.save(newUser);
    }
}
