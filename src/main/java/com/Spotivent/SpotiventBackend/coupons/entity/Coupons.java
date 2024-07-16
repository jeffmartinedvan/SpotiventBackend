package com.Spotivent.SpotiventBackend.coupons.entity;

import com.Spotivent.SpotiventBackend.events.entity.Events;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "coupons")
public class Coupons {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "event_id")
    @JsonIgnore
    private Events events;

    @NotNull(message = "Coupon name must not be null")
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull(message = "Discount must not be null")
    @Column(name = "discounts", nullable = false)
    private Double discounts;

    @NotNull(message = "Limits must not be null")
    @Column(name = "limits", nullable = false)
    private Integer limits;

    @NotNull(message = "Referral promo must not be null")
    @Column(name = "is_referral_promo", nullable = false)
    private Boolean isReferralPromo;

    @NotNull(message = "Start date must not be null")
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @NotNull(message = "End date must not be null")
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = Instant.now();
    }
}
