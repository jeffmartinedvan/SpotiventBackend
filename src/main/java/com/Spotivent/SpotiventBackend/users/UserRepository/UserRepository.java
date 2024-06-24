package com.Spotivent.SpotiventBackend.users.UserRepository;

import com.Spotivent.SpotiventBackend.users.UserEntity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
}
