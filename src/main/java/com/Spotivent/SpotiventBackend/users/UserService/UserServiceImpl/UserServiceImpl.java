package com.Spotivent.SpotiventBackend.users.UserService.UserServiceImpl;

import com.Spotivent.SpotiventBackend.users.DTO.RegisterRequestDTO;
import com.Spotivent.SpotiventBackend.users.UserEntity.Users;
import com.Spotivent.SpotiventBackend.users.UserRepository.UserRepository;
import com.Spotivent.SpotiventBackend.users.UserService.UserService;
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
        newUser.setName(registerRequestDTO.getName());
        newUser.setRole(registerRequestDTO.getRole());
        return userRepository.save(newUser);
    }
}
