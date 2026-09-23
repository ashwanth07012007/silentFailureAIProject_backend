package com.backend.Service;

import com.backend.Model.AIResponse;
import com.backend.Model.SensorRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AIService {

    private final RestTemplate restTemplate;

    private final String PYTHON_AI_URL =
            System.getenv("PYTHON_AI_URL");


    public AIService() {

        this.restTemplate = new RestTemplate();

    }


    public AIResponse predict(SensorRequest request) {

        return restTemplate.postForObject(
                PYTHON_AI_URL,
                request,
                AIResponse.class
        );

    }
}