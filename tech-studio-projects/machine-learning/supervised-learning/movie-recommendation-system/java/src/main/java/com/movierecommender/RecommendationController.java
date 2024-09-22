package com.movierecommender;

import com.movierecommender.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    @GetMapping("/generate-recommendations")
    public String generateRecommendations() {
        recommendationService.generateRecommendations();
        return "Recommendation generation started!";
    }
}
