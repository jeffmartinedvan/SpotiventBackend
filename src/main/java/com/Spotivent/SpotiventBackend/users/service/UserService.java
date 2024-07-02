package com.Spotivent.SpotiventBackend.users.service;

import com.Spotivent.SpotiventBackend.users.dto.RegisterRequestDTO;
import com.Spotivent.SpotiventBackend.users.entity.Users;

public interface UserService {
    Users register(RegisterRequestDTO registerRequestDTO);
}
