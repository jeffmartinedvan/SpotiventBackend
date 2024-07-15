package com.Spotivent.SpotiventBackend.tickets.repository;

import com.Spotivent.SpotiventBackend.tickets.entity.Tickets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Tickets, Long> {
    List<Tickets> findByEventsId(Long eventId);
}
