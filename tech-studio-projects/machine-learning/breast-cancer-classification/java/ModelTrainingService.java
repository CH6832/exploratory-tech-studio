package com.example.service;

import org.springframework.stereotype.Service;

@Service
public class ModelTrainingService {

    public double trainModel(String dataPath, String[] features) {
        // Implement model training logic
        // For demonstration, return a dummy accuracy
        return 0.85;
    }
}
