package com.Spotivent.SpotiventBackend.users.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.Instant;

@Data
public class RegisterRequestDTO {
    @NotNull
    private String role;

    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull
    private String password;
}
