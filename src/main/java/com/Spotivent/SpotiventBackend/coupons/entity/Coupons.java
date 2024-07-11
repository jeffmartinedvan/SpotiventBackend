package com.Spotivent.SpotiventBackend.coupons.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Entity
@Data
@Table(name = "coupons")
public class Coupons {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

//    private Events events;

    @NotNull(message = "Discount must not be null")
    @Column(name = "discounts", nullable = false)
    private Double discounts;

    @NotNull(message = "Limit must not be null")
    @Column(name = "limit", nullable = false)
    private Integer limits;

    @NotNull(message = "Referral promo must not be null")
    @Column(name = "is_referral_promo", nullable = false)
    private Boolean isReferralPromo;

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
