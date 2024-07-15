package com.Spotivent.SpotiventBackend.referrals.service;

import com.Spotivent.SpotiventBackend.referrals.dto.CreateReferralRequestDTO;
import com.Spotivent.SpotiventBackend.referrals.entity.Referrals;

public interface ReferralService {
    Referrals createReferralCode(CreateReferralRequestDTO createReferralRequestDTO);
    Referrals getByUsersId(Long id);
}
