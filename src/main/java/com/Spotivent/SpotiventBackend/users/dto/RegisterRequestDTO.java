package com.Spotivent.SpotiventBackend.users.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterRequestDTO {
    @NotNull
    private String role;

    @NotNull
    private String username;

    @NotNull
    private String email;

    @NotNull
    private String password;
}
