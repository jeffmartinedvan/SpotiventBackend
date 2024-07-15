package com.Spotivent.SpotiventBackend.events.repository;

import com.Spotivent.SpotiventBackend.events.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    City findByName(String name);
}
