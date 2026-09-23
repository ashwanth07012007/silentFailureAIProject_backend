package com.backend.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "sensor_readings")
@Data
public class SensorReading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double temperature;

    private double current;

    private double vibration;

    private double pressure;

    private double risk;

    private boolean anomaly;

    private String riskTrend;

    private String status;

    @Column(length = 500)
    private String recommendation;

    private LocalDateTime createdAt;

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
    }
}