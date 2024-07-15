package com.Spotivent.SpotiventBackend.events.repository;

import com.Spotivent.SpotiventBackend.events.entity.Events;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Events, Long> {
    Page<Events> findAll(Specification<Events> specification, Pageable pageable);
}
