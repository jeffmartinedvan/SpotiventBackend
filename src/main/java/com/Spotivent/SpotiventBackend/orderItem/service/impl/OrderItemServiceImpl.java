package com.Spotivent.SpotiventBackend.orderItem.service.impl;

import com.Spotivent.SpotiventBackend.exception.ApplicationException;
import com.Spotivent.SpotiventBackend.orderItem.dto.CreateOrderItemRequestDTO;
import com.Spotivent.SpotiventBackend.orderItem.dto.OrderItemResponseDTO;
import com.Spotivent.SpotiventBackend.orderItem.entity.OrderItems;
import com.Spotivent.SpotiventBackend.orderItem.repository.OrderItemRepository;
import com.Spotivent.SpotiventBackend.orderItem.service.OrderItemService;
import com.Spotivent.SpotiventBackend.tickets.entity.Tickets;
import com.Spotivent.SpotiventBackend.tickets.repository.TicketRepository;
import com.Spotivent.SpotiventBackend.transactions.entity.Transactions;
import com.Spotivent.SpotiventBackend.transactions.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final TicketRepository ticketRepository;
    private final TransactionRepository transactionRepository;

    public OrderItemServiceImpl(OrderItemRepository orderItemRepository, TicketRepository ticketRepository, TransactionRepository transactionRepository) {
        this.orderItemRepository = orderItemRepository;
        this.ticketRepository = ticketRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public OrderItemResponseDTO createOrderItem(CreateOrderItemRequestDTO requestDTO, Long transactionId) {
        Tickets ticket = ticketRepository.findById(requestDTO.getTicketId())
                .orElseThrow(() -> new ApplicationException("Ticket not found"));
        Transactions transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new ApplicationException("Transaction not found"));

        OrderItems orderItem = new OrderItems();
        orderItem.setTickets(ticket);
        orderItem.setTransactions(transaction);
        orderItem.setTicketQty(requestDTO.getTicketQty());
        orderItem = orderItemRepository.save(orderItem);

        return mapToOrderItemResponseDTO(orderItem);
    }

    @Override
    public List<OrderItemResponseDTO> getOrderItemsByTransaction(Long transactionId) {
        List<OrderItems> orderItems = orderItemRepository.findAll()
                .stream()
                .filter(orderItem -> orderItem.getTransactions().getId().equals(transactionId))
                .toList();

        return orderItems.stream().map(this::mapToOrderItemResponseDTO).collect(Collectors.toList());
    }

    private OrderItemResponseDTO mapToOrderItemResponseDTO(OrderItems orderItem) {
        OrderItemResponseDTO responseDTO = new OrderItemResponseDTO();
        responseDTO.setId(orderItem.getId());
        responseDTO.setTicketId(orderItem.getTickets().getId());
        responseDTO.setTicketQty(orderItem.getTicketQty());
        responseDTO.setTransactionId(orderItem.getTransactions().getId());
        return responseDTO;
    }
}