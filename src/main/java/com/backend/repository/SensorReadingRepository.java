package com.backend.repository;

import com.backend.Model.SensorReading;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SensorReadingRepository
        extends JpaRepository<SensorReading, Long> {

    List<SensorReading> findTop20ByOrderByCreatedAtDesc();
}