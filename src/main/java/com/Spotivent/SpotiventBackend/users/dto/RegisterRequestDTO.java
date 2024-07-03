package com.Spotivent.SpotiventBackend.users.dto;

import com.Spotivent.SpotiventBackend.users.entity.Roles;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterRequestDTO {
    @NotNull
    @Enumerated(EnumType.STRING)
    private Roles role;

    @NotNull
    private String username;

    @NotNull
    private String email;

    @NotNull
    private String password;
}
