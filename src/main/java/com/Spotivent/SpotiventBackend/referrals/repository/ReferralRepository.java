package com.Spotivent.SpotiventBackend.referrals.repository;

import com.Spotivent.SpotiventBackend.referrals.entity.Referrals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReferralRepository extends JpaRepository<Referrals, Long> {
    Referrals findByUsersId(Long id);
}
