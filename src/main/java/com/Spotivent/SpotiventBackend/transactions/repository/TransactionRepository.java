package com.Spotivent.SpotiventBackend.transactions.repository;

import com.Spotivent.SpotiventBackend.transactions.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions, Long> {
    List<Transactions> findByUsersId(Long userId);
    List<Transactions> findByEventsId(Long eventId);
}
