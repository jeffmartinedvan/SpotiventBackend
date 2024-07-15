package com.Spotivent.SpotiventBackend.events.repository;

import com.Spotivent.SpotiventBackend.events.entity.Events;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.Objects;

public class EventSpecification {
    public static Specification<Events> byEventName(String eventName) {
        return ((root, query, cb) -> {
            if (eventName == null) {
                return cb.conjunction();
            }
            if (eventName.isEmpty()) {
                return cb.equal(root.get("eventName"), "");
            }
            return cb.like(cb.lower(root.get("eventName")), "%" + eventName.toLowerCase() + "%");
        });
    }

    public static Specification<Events> byCategory(String category) {
        return ((root, query, cb) -> {
            if (category == null) {
                return cb.conjunction();
            }
            return cb.equal(cb.lower(root.get("category")), category.toLowerCase());
        });
    }

    public static Specification<Events> byCity(String city) {
        return ((root, query, cb) -> {
            if (city == null) {
                return cb.conjunction();
            }
            return cb.equal(cb.lower(root.get("city")), city.toLowerCase());
        });
    }

    public static Specification<Events> byUserId(Long userId) {
        return ((root, query, cb)-> {
            if (userId == null) {
                return cb.conjunction();
            }
            return cb.equal(root.get("user").get("id"), userId);
        });
    }

    public static Specification<Events> byUpcoming(String upcoming) {
        return (root, query, cb) -> {
            if (Objects.equals(upcoming, "true")) {
                LocalDateTime targetDate = LocalDateTime.now().plusDays(30);
                return cb.greaterThanOrEqualTo(root.get("date"), targetDate);
            }
            return cb.conjunction();
        };
    }
}