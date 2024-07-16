package com.Spotivent.SpotiventBackend.auth.service;

import com.Spotivent.SpotiventBackend.auth.dto.LoginResponseDto;
import org.springframework.security.core.Authentication;

public interface AuthService {
    LoginResponseDto generateToken(Authentication authentication);
}
