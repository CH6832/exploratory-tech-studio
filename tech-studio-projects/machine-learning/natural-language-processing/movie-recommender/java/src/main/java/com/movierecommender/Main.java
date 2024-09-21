package com.movierecommender;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.mllib.recommendation.ALS;
import org.apache.spark.mllib.recommendation.MatrixFactorizationModel;
import org.apache.spark.mllib.recommendation.Rating;
import scala.Tuple2;

public class Main {

    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("MovieRecommender").setMaster("local[*]");
        try (JavaSparkContext sc = new JavaSparkContext(conf)) {
            // Load and parse ratings data
            String ratingsPath = "ratings.txt";
            JavaRDD<String> ratingsData = sc.textFile(ratingsPath);
            JavaRDD<Rating> ratings = ratingsData.map(line -> {
                String[] parts = line.split(",");
                return new Rating(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Double.parseDouble(parts[2]));
            });

            // Build the recommendation model using ALS
            int rank = 10;
            int numIterations = 10;
            MatrixFactorizationModel model = ALS.train(ratings.rdd(), rank, numIterations, 0.01);

            // Load movie titles data
            String moviesPath = "movies.txt";
            JavaPairRDD<Integer, String> movieTitles = sc.textFile(moviesPath)
                    .mapToPair(line -> {
                        String[] parts = line.split(",");
                        return new Tuple2<>(Integer.parseInt(parts[0]), parts[1]);
                    });

            // Generate top 10 movie recommendations for each user
            JavaPairRDD<Object, Object> userRecommendations = model.recommendProductsForUsers(10)
                    .toJavaRDD().mapToPair(pair -> new Tuple2<>(pair._1(), pair._2()));

            // Output user recommendations
            userRecommendations.collect().forEach(user -> {
                System.out.println("User: " + user._1());
                Rating[] ratings1 = (Rating[]) user._2(); // Explicit casting
                for (Rating rating : ratings1) {
                    String movieTitle = movieTitles.lookup(rating.product()).get(0);
                    System.out.println("  Movie: " + movieTitle + ", Rating: " + rating.rating());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
