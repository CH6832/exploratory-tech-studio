package com.wordcount;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaFutureAction;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.*;
import java.util.concurrent.ExecutionException;

public class WordCount {

    public void run() {
        // Create a SparkConf object to configure the Spark application
        SparkConf conf = new SparkConf().setAppName("WordCount").set("spark.speculation", "false")
                .set("spark.hadoop.mapreduce.application.framework.path", "/user/spark/share/lib/hadoop/mapreduce/hadoop-mapreduce-client-core-2.7.4.jar")
                .set("spark.yarn.jars", "hdfs://<namenode>:<port>/user/spark/share/lib/*.jar")
                .set("spark.driver.host", "localhost")
                .set("spark.executor.userClassPathFirst", "true")
                .set("spark.driver.userClassPathFirst", "true")
                .set("spark.driver.extraJavaOptions", "-Dio.netty.tryReflectionSetAccessible=true")
                .set("spark.executor.extraJavaOptions", "-Dio.netty.tryReflectionSetAccessible=true")
                .set("spark.driver.host", "localhost") // Add this line to avoid driver host issues
                .set("spark.executor.userClassPathFirst", "true") // Add this line to prioritize Spark's built-in classes
                .set("spark.driver.userClassPathFirst", "true");

        // Create a JavaSparkContext object to interact with Spark
        try (JavaSparkContext sc = new JavaSparkContext(conf)) {

            // Read input text file into an RDD (Resilient Distributed Dataset)
            JavaRDD<String> lines = sc.textFile("input.txt");

            // Split each line into words and flatten the resulting collections of words
            JavaRDD<String> words = lines.flatMap(line -> Arrays.asList(line.split(" ")).iterator());

            // Map each word to a tuple (word, 1) representing word count
            JavaRDD<Map.Entry<String, Integer>> wordCounts = words.mapToPair(word -> new Tuple2<>(word, 1))
                    .reduceByKey(Integer::sum)
                    .map(entry -> new AbstractMap.SimpleEntry<>(entry._1, entry._2));

            // Collect the word counts RDD into a Java Map and print each entry
            // Collect the word counts RDD asynchronously into a list of map entries
            JavaFutureAction<List<Map.Entry<String, Integer>>> futureWordCounts = wordCounts.collectAsync();

            // Wait for the asynchronous action to complete and get the result
            List<Map.Entry<String, Integer>> wordCountsList = futureWordCounts.get();

            // Convert the list of map entries to a map
            Map<String, Integer> wordCountsMap = new HashMap<>();
            for (Map.Entry<String, Integer> entry : wordCountsList) {
                wordCountsMap.put(entry.getKey(), entry.getValue());
            }

            // Print each entry in the wordCountsMap
            for (Map.Entry<String, Integer> entry : wordCountsMap.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }

            // Stop the Spark context to release resources
            sc.stop();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}