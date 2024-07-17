package com.Spotivent.SpotiventBackend.transactions.controller;

import com.Spotivent.SpotiventBackend.auth.helper.Claims;
import com.Spotivent.SpotiventBackend.transactions.dto.CreateTransactionRequestDTO;
import com.Spotivent.SpotiventBackend.transactions.dto.TransactionResponseDTO;
import com.Spotivent.SpotiventBackend.transactions.services.TransactionService;
import com.Spotivent.SpotiventBackend.response.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transaction")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<Response<TransactionResponseDTO>> createTransaction(@RequestBody CreateTransactionRequestDTO request) {
        String email = (String) Claims.getClaims().get("sid");
        return Response.success("Transaction created successfully", transactionService.createTransaction(request, email));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Response<List<TransactionResponseDTO>>> getTransactionsByUserId(@PathVariable Long userId) {
        return Response.success("Transactions fetched successfully", transactionService.getTransactionsByUserId(userId));
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<Response<List<TransactionResponseDTO>>> getTransactionsByEventId(@PathVariable Long eventId) {
        return Response.success("Transactions fetched successfully", transactionService.getTransactionsByEventId(eventId));
    }
}