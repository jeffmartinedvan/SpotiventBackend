package com.Spotivent.SpotiventBackend.events.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Entity
@Data
@Table(name = "cities")
public class Cities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

//    private Events events;

    @NotNull(message = "Event city must not be null")
    @Column(name = "name", nullable = false)
    @Enumerated(EnumType.STRING)
    private CityEnum city;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = Instant.now();
    }
}
