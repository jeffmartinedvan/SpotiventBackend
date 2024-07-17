package com.Spotivent.SpotiventBackend.transactions.services;

import com.Spotivent.SpotiventBackend.orderItem.entity.OrderItems;
import com.Spotivent.SpotiventBackend.transactions.dto.CreateTransactionRequestDTO;
import com.Spotivent.SpotiventBackend.transactions.dto.TransactionResponseDTO;
import com.Spotivent.SpotiventBackend.transactions.entity.Transactions;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface TransactionService {
    TransactionResponseDTO createTransaction(CreateTransactionRequestDTO request, String email);
    Transactions getDetailById(Long id);
    List<TransactionResponseDTO> getTransactionsByUserId(Long userId);
    List<TransactionResponseDTO> getTransactionsByEventId(Long eventId);
}