package com.Spotivent.SpotiventBackend.referrals.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateReferralRequestDTO {
    @NotNull
    private Long userId;

    @NotNull
    private Boolean claimed;

    private Long referrerId;
}