package com.Spotivent.SpotiventBackend.users.service;

import com.Spotivent.SpotiventBackend.users.dto.RegisterRequestDTO;
import com.Spotivent.SpotiventBackend.users.dto.RegisterResponseDTO;
import com.Spotivent.SpotiventBackend.users.entity.Users;

public interface UserService {
    RegisterResponseDTO register(RegisterRequestDTO registerRequestDTO);
    RegisterResponseDTO getUserById(Long id);
    Users getDetailUser(Long id);
}
