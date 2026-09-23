package com.backend.Model;

import lombok.Data;

@Data
public class SensorRequest {

    private double temperature;

    private double current;

    private double vibration;

    private double pressure;
}