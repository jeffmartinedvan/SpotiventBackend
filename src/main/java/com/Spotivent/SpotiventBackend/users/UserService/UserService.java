package com.Spotivent.SpotiventBackend.users.UserService;

import com.Spotivent.SpotiventBackend.users.DTO.RegisterRequestDTO;
import com.Spotivent.SpotiventBackend.users.UserEntity.Users;

public interface UserService {
    Users register(RegisterRequestDTO registerRequestDTO);
}
