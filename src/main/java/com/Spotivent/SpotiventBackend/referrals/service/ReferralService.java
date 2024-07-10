package com.Spotivent.SpotiventBackend.referrals.service;

import com.Spotivent.SpotiventBackend.referrals.dto.CreateReferralRequestDTO;
import com.Spotivent.SpotiventBackend.referrals.entity.Referrals;
import org.springframework.stereotype.Service;

@Service
public interface ReferralService {
    Referrals createReferralCode(CreateReferralRequestDTO createReferralRequestDTO);
    Referrals getByUserId(Long id);
}
