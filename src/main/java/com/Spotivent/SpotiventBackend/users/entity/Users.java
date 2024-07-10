package com.Spotivent.SpotiventBackend.users.entity;

import com.Spotivent.SpotiventBackend.referrals.entity.Referrals;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.LinkedHashSet;
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
    @Column(name = "email", nullable = false)
    private String email;

    @NotNull(message = "Password must not be null")
    @Column(name = "password", nullable = false)
    private String password;

    @NotNull(message = "Role must not be null")
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Roles role;

    @NotNull(message = "Username must not be null")
    @Column(name = "username", nullable = false)
    private String username;

    @OneToMany(mappedBy = "user")
    private Set<Referrals> referrals = new LinkedHashSet<>();

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
