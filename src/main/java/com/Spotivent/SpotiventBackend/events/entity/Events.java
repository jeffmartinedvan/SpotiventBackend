package com.Spotivent.SpotiventBackend.events.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@Table(name = "events")
public class Events {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

//    private Cities cities;

//    private Categories categories;

//    private Reviews reviews;

//    private Users users;

    @NotNull(message = "Event name must not be null")
    @Column(name = "event_name", nullable = false)
    private String eventName;

    @NotNull(message = "Thumbnail must not be null")
    @Column(name = "thumbnail", nullable = false)
    private String thumbnail;

    @Column(name = "image")
    private String image;

    @NotNull(message = "Location must not be null")
    @Column(name = "location", nullable = false)
    private String location;

    @NotNull(message = "Event date must not be null")
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @NotNull(message = "Start time must not be null")
    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @NotNull(message = "End time must not be null")
    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    @NotNull(message = "Description must not be null")
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull(message = "Is event free must not be null")
    @Column(name = "free_event", nullable = false)
    private Boolean isFree;

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
