package com.movierecommender

object Main extends App {
  val recommendationService = new RecommendationService
  recommendationService.generateRecommendations()
}

