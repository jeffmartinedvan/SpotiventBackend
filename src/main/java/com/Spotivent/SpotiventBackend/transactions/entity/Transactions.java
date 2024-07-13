package com.Spotivent.SpotiventBackend.transactions.entity;

import com.Spotivent.SpotiventBackend.coupons.entity.Coupons;
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
@Table(name = "transactions")
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @OneToMany(mappedBy = "transactions", cascade = CascadeType.ALL)
    private Set<OrderItems> orderItems;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private Users users;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "event_id", nullable = false)
    @JsonIgnore
    private Events events;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coupon_id")
    @JsonIgnore
    private Coupons coupons;

    @NotNull(message = "Original price must not be null")
    @Column(name = "original_price", nullable = false)
    private Long originalPrice;

    @Column(name = "total_discount")
    private Long totalDiscount;

    @Column(name = "use_points")
    private Long usePoints;

    @NotNull(message = "Total price must not be null")
    @Column(name = "total_price", nullable = false)
    private Long totalPrice;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = Instant.now();
    }
}
