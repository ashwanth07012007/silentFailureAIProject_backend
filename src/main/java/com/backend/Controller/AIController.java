package com.backend.Controller;

import com.backend.Model.AIResponse;
import com.backend.Model.SensorRequest;
import com.backend.Service.AIService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
@CrossOrigin(origins = "*")
public class AIController {

    private final AIService aiService;

    public AIController(AIService aiService) {
        this.aiService = aiService;
    }

    @PostMapping("/predict")
    public AIResponse predict(
            @RequestBody SensorRequest request
    ) {

        return aiService.predict(request);
    }
}