package com.Spotivent.SpotiventBackend.referrals.dto;

import lombok.Data;

@Data
public class CreateReferralRequestDTO {
    private Long userId;
    private Boolean claimed;
}