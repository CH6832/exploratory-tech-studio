package com.movierecommender

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.mllib.recommendation.{ALS, MatrixFactorizationModel, Rating}
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service
class RecommendationService {

  @Async
  def generateRecommendations(): Unit = {
    val conf = new SparkConf().setAppName("MovieRecommender").setMaster("local[*]")
    val sc = new SparkContext(conf)

    try {
      // Load and parse ratings data
      val ratingsPath = "data/ratings.txt"
      val ratingsData = sc.textFile(ratingsPath)
      val ratings = ratingsData.map { line =>
        val parts = line.split(",")
        Rating(parts(0).toInt, parts(1).toInt, parts(2).toDouble)
      }

      // Build the recommendation model using ALS
      val rank = 10
      val numIterations = 10
      val lambda = 0.01
      val model = ALS.train(ratings, rank, numIterations, lambda)

      // Load movie titles data
      val moviesPath = "data/movies.txt"
      val movieTitles = sc.textFile(moviesPath).map { line =>
        val parts = line.split(",")
        (parts(0).toInt, parts(1))
      }.collectAsMap()

      // Broadcast movie titles to optimize performance
      val movieTitlesBroadcast = sc.broadcast(movieTitles)

      // Generate top 10 movie recommendations for each user
      val userRecommendations = model.recommendProductsForUsers(10)

      // Output user recommendations
      userRecommendations.foreach { case (user, recommendations) =>
        println(s"User: $user")
        recommendations.foreach { recommendation =>
          val movieTitle = movieTitlesBroadcast.value.getOrElse(recommendation.product, "Unknown")
          println(s"  Movie: $movieTitle, Rating: ${recommendation.rating}")
        }
      }
    } catch {
      case e: Exception => e.printStackTrace()
    } finally {
      sc.stop()
    }
  }
}
