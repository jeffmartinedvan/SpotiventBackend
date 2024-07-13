package com.Spotivent.SpotiventBackend.users.repository;

import com.Spotivent.SpotiventBackend.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByEmail(String username);

    Optional<Users> findByReferralCode(String referralCode);
}
