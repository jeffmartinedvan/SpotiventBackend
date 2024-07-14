package com.Spotivent.SpotiventBackend.users.dto;

import com.Spotivent.SpotiventBackend.users.entity.Roles;
import lombok.Data;

import java.time.Instant;

@Data
public class RegisterResponseDTO {
    private long id;
    private String avatar;
    private String email;
    private Roles role;
    private String username;
    private String referralCode;
}
