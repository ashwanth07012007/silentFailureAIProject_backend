package com.backend.Controller;

import com.backend.Model.AIResponse;
import com.backend.Model.SensorReading;
import com.backend.Model.SensorRequest;
import com.backend.repository.SensorReadingRepository;
import com.backend.Service.AIService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sensors")
@CrossOrigin(origins = "*")
public class SensorController {

    private final SensorReadingRepository repository;

    private final AIService aiService;


    public SensorController(
            SensorReadingRepository repository,
            AIService aiService) {

        this.repository = repository;

        this.aiService = aiService;
    }


    // ==========================================
    // RECEIVE SENSOR DATA
    // ==========================================

    @PostMapping
    public ResponseEntity<SensorReading> receiveSensorData(
            @RequestBody SensorRequest request) {


        // --------------------------------------
        // SEND DATA TO PYTHON AI
        // --------------------------------------

        AIResponse aiResponse =
                aiService.predict(request);


        // --------------------------------------
        // CREATE DATABASE RECORD
        // --------------------------------------

        SensorReading reading =
                new SensorReading();


        // Sensor values

        reading.setTemperature(
                request.getTemperature()
        );

        reading.setCurrent(
                request.getCurrent()
        );

        reading.setVibration(
                request.getVibration()
        );

        reading.setPressure(
                request.getPressure()
        );


        // --------------------------------------
        // AI RESULTS
        // --------------------------------------

        reading.setRisk(
                aiResponse.getRisk()
        );

        reading.setAnomaly(
                aiResponse.isAnomaly()
        );

        reading.setRiskTrend(
                aiResponse.getRiskTrend()
        );

        reading.setStatus(
                aiResponse.getStatus()
        );

        reading.setRecommendation(
                aiResponse.getRecommendation()
        );


        // --------------------------------------
        // SAVE TO POSTGRESQL
        // --------------------------------------

        SensorReading savedReading =
                repository.save(reading);


        return ResponseEntity.ok(savedReading);
    }


    // ==========================================
    // GET ALL READINGS
    // ==========================================

    @GetMapping
    public ResponseEntity<List<SensorReading>> getAllReadings() {

        return ResponseEntity.ok(
                repository.findAll()
        );
    }


    // ==========================================
    // GET LATEST READINGS
    // ==========================================

    @GetMapping("/latest")
    public ResponseEntity<List<SensorReading>> getLatestReadings() {

        return ResponseEntity.ok(
                repository.findTop20ByOrderByCreatedAtDesc()
        );
    }
}