package com.Spotivent.SpotiventBackend.users.dto;

import com.Spotivent.SpotiventBackend.users.entity.Roles;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterRequestDTO {
    @Enumerated(EnumType.STRING)
    private Roles role;

    private String username;

    private String email;

    private String password;

    private String referralCode;
}
