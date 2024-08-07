package com.Spotivent.SpotiventBackend.users.entity;

import com.Spotivent.SpotiventBackend.events.entity.Events;
import com.Spotivent.SpotiventBackend.reviews.entity.Reviews;
import com.Spotivent.SpotiventBackend.points.entity.Points;
import com.Spotivent.SpotiventBackend.referrals.entity.Referrals;
import com.Spotivent.SpotiventBackend.transactions.entity.Transactions;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.Set;

@Entity
@Data
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "avatar")
    private String avatar;

    @NotNull(message = "Email must not be null")
    @Column(name = "email", nullable = false, length = 100)
    @Email
    private String email;

    @NotNull(message = "Password must not be null")
    @Column(name = "password", nullable = false, length = 100)
    @JsonIgnore
    private String password;

    @NotNull(message = "Role must not be null")
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Roles role;

    @NotNull(message = "Username must not be null")
    @Column(name = "username", nullable = false, length = 100)
    private String username;

    @Column(name = "referral_code")
    private String referralCode;

    @OneToOne(mappedBy = "users", cascade = CascadeType.ALL)
    private Referrals referrals;

    @JsonIgnore
    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private Set<Points> points;

    @JsonIgnore
    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private Set<Events> events;

    @JsonIgnore
    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private Set<Transactions> transactions;

    @JsonIgnore
    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private Set<Reviews> reviews;

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
