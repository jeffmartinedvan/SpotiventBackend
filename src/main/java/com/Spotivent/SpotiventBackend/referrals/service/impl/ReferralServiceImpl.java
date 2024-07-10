package com.Spotivent.SpotiventBackend.referrals.service.impl;

import com.Spotivent.SpotiventBackend.referrals.dto.CreateReferralRequestDTO;
import com.Spotivent.SpotiventBackend.referrals.entity.Referrals;
import com.Spotivent.SpotiventBackend.referrals.repository.ReferralRepository;
import com.Spotivent.SpotiventBackend.referrals.service.ReferralService;
import com.Spotivent.SpotiventBackend.users.service.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReferralServiceImpl implements ReferralService {
    private final ReferralRepository referralRepository;
    private final UserService userService;

    public ReferralServiceImpl(ReferralRepository referralRepository, @Lazy UserService userService) {
        this.referralRepository = referralRepository;
        this.userService = userService;
    }

    @Override
    public Referrals createReferralCode(CreateReferralRequestDTO createReferralRequestDTO) {
        Referrals referrals = new Referrals();
        referrals.setReferralCode(UUID.randomUUID().toString().replace("-", "").substring(0,6));
        referrals.setUser(userService.getDetailUser(createReferralRequestDTO.getUserId()));
        referrals.setClaimed(createReferralRequestDTO.getClaimed());
        return referralRepository.save(referrals);
    }

    @Override
    public Referrals getByUserId(Long id) {
        return referralRepository.findByUserId(id);
    }
}
