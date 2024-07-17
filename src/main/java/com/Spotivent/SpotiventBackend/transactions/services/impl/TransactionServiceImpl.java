package com.Spotivent.SpotiventBackend.transactions.services.impl;

import com.Spotivent.SpotiventBackend.coupons.dto.CouponResponseDTO;
import com.Spotivent.SpotiventBackend.coupons.entity.Coupons;
import com.Spotivent.SpotiventBackend.coupons.services.CouponService;
import com.Spotivent.SpotiventBackend.events.entity.Events;
import com.Spotivent.SpotiventBackend.events.service.EventService;
import com.Spotivent.SpotiventBackend.orderItem.entity.OrderItems;
import com.Spotivent.SpotiventBackend.orderItem.service.OrderItemService;
import com.Spotivent.SpotiventBackend.transactions.dto.CreateTransactionRequestDTO;
import com.Spotivent.SpotiventBackend.transactions.dto.TransactionResponseDTO;
import com.Spotivent.SpotiventBackend.transactions.entity.Transactions;
import com.Spotivent.SpotiventBackend.transactions.repository.TransactionRepository;
import com.Spotivent.SpotiventBackend.transactions.services.TransactionService;
import com.Spotivent.SpotiventBackend.users.entity.Users;
import com.Spotivent.SpotiventBackend.users.service.UserService;
import com.Spotivent.SpotiventBackend.exception.ApplicationException;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final UserService userService;
    private final EventService eventService;
    private final OrderItemService orderItemService;
    private final CouponService couponService;

    public TransactionServiceImpl(TransactionRepository transactionRepository, UserService userService,
                                  EventService eventService, @Lazy OrderItemService orderItemService, CouponService couponService) {
        this.transactionRepository = transactionRepository;
        this.userService = userService;
        this.eventService = eventService;
        this.orderItemService = orderItemService;
        this.couponService = couponService;
    }

    @Override
    @Transactional
    public TransactionResponseDTO createTransaction(CreateTransactionRequestDTO request, String email) {
        Users user = userService.findByEmail(email);

        Events event = eventService.getEventById(request.getEventId());
        if (event == null) {
            throw new ApplicationException("Event not found");
        }

        Coupons coupon = couponService.getDetailCoupon(request.getCouponId());
        if (request.getCouponId() != null) {
            CouponResponseDTO responseDTO = couponService.getCouponById(request.getCouponId());
            if (responseDTO == null) {
                throw new ApplicationException("Coupon not found");
            }
        }

        Transactions newTransaction = new Transactions();
        newTransaction.setUsers(user);
        newTransaction.setEvents(event);
        newTransaction.setCoupons(coupon);
        newTransaction.setOriginalPrice(request.getOriginalPrice());
        newTransaction.setTotalDiscount(request.getTotalDiscount());
        newTransaction.setUsePoints(request.getUsePoints());
        newTransaction.setTotalPrice(request.getTotalPrice());

        Transactions saved = transactionRepository.save(newTransaction);

        // Save order items
        Set<OrderItems> orderItems = orderItemService.getOrderItemsByIds(request.getOrderItemIds());
        Transactions finalTransaction = newTransaction;
        orderItems.forEach(orderItem -> {
            orderItem.setTransactions(finalTransaction);
            orderItemService.saveOrderItem(orderItem);
        });

        return mapToTransactionResponseDTO(newTransaction);
    }

    @Override
    public Transactions getDetailById(Long id) {
        return transactionRepository.findById(id).orElseThrow(() -> new ApplicationException("Transaction not found"));
    }

    @Override
    public List<TransactionResponseDTO> getTransactionsByUserId(Long userId) {
        return transactionRepository.findByUsersId(userId)
                .stream()
                .map(this::mapToTransactionResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TransactionResponseDTO> getTransactionsByEventId(Long eventId) {
        return transactionRepository.findByEventsId(eventId)
                .stream()
                .map(this::mapToTransactionResponseDTO)
                .collect(Collectors.toList());
    }

    private TransactionResponseDTO mapToTransactionResponseDTO(Transactions transaction) {
        TransactionResponseDTO responseDTO = new TransactionResponseDTO();
        responseDTO.setId(transaction.getId());
        responseDTO.setUserId(transaction.getUsers().getId());
        responseDTO.setEventId(transaction.getEvents().getId());
        if (transaction.getCoupons() != null) {
            responseDTO.setCouponId(transaction.getCoupons().getId());
        }
        responseDTO.setOriginalPrice(transaction.getOriginalPrice());
        responseDTO.setTotalDiscount(transaction.getTotalDiscount());
        responseDTO.setUsePoints(transaction.getUsePoints());
        responseDTO.setTotalPrice(transaction.getTotalPrice());
        responseDTO.setCreatedAt(transaction.getCreatedAt());
        return responseDTO;
    }
}