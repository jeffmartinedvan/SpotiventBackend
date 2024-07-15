package com.Spotivent.SpotiventBackend.events.repository;

import com.Spotivent.SpotiventBackend.events.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);
}
