package com.Spotivent.SpotiventBackend.tickets.entity;

import com.Spotivent.SpotiventBackend.events.entity.Events;
import com.Spotivent.SpotiventBackend.orderItem.entity.OrderItems;
import com.Spotivent.SpotiventBackend.users.entity.Users;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.Set;

@Entity
@Data
@Table(name = "tickets")
public class Tickets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "event_id", nullable = false)
    @JsonIgnore
    private Events events;

    @NotNull
    @OneToMany(mappedBy = "tickets", cascade = CascadeType.ALL)
    private Set<OrderItems> orderItems;

    @NotNull(message = "Ticket type must not be null")
    @Column(name = "type", nullable = false)
    private String type;

    @NotNull(message = "Ticket price must not be null")
    @Column(name = "price", nullable = false)
    private Long price;

    @NotNull(message = "Ticket available seats must not be null")
    @Column(name = "available_seat", nullable = false)
    private Integer availableSeat;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @Column(name = "deleted_at")
    private Instant deletedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = Instant.now();
    }

    @PreRemove
    public void preRemove() {
        this.deletedAt = Instant.now();
    }
}
