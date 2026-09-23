package com.backend.Model;

import lombok.Data;

import java.util.List;

@Data
public class AIResponse {

    private double temperature;

    private double current;

    private double vibration;

    private double pressure;

    private boolean anomaly;

    private double risk;

    private List<Double> riskHistory;

    private double earlyAvgRisk;

    private double recentAvgRisk;

    private double riskChange;

    private String riskTrend;

    private String status;

    private String recommendation;
}