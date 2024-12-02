import org.apache.commons.csv.{CSVFormat, CSVParser}
import scala.io.Source
import java.io.{FileWriter}
import scala.util.Using
import scala.jdk.CollectionConverters._

class EnergyPriceForecaster {

  private val modelPath = "model/energy-price-model.txt"

  // Train a simple linear regression model using historical data
  def trainModel(): Unit = {
    println("Training the model...")

    val data = loadHistoricalData("data/historical_energy_prices.csv")

    // Extract features and labels
    val features = data.map(_._1)
    val labels = data.map(_._2)

    // Simple linear regression (least squares solution)
    val n = features.length
    val sumX = features.sum
    val sumY = labels.sum
    val sumXY = features.zip(labels).map { case (x, y) => x * y }.sum
    val sumX2 = features.map(x => x * x).sum

    val slope = (n * sumXY - sumX * sumY) / (n * sumX2 - sumX * sumX)
    val intercept = (sumY - slope * sumX) / n

    // Save model parameters to a file
    Using(new FileWriter(modelPath)) { writer =>
      writer.write(s"$slope,$intercept")
    }.get

    println(s"Model training completed. Slope: $slope, Intercept: $intercept")
  }

  // Predict energy prices using the trained model
  def predict(inputFeature: Double): Double = {
    println("Loading model and making prediction...")

    // Load model parameters from file
    val Array(slope, intercept) = Using(Source.fromFile(modelPath)) { source =>
      source.getLines().next().split(",").map(_.toDouble)
    }.get

    // Calculate prediction
    val prediction = slope * inputFeature + intercept
    println(s"Prediction complete. Predicted price: $$${prediction}")
    prediction
  }

  // Load historical data from a CSV file
  private def loadHistoricalData(filePath: String): Seq[(Double, Double)] = {
    println(s"Loading historical data from $filePath...")

    val reader = Source.fromFile(filePath)
    val lines = reader.getLines().drop(1) // Skip the header row

    val data = lines.map { line =>
      val Array(timestamp, feature, price) = line.split(",").map(_.trim) // Split by comma, trim spaces

      try {
        // Parse only 'feature' and 'price' columns, skip 'timestamp'
        val featureValue = feature.toDouble
        val priceValue = price.toDouble

        (featureValue, priceValue)  // Return as tuple (feature, price)
      } catch {
        case e: NumberFormatException =>
          println(s"Skipping invalid data line: $line")
          // Return a default value or skip this line depending on your needs
          (0.0, 0.0)
      }
    }.toSeq.filterNot { case (f, p) => f == 0.0 && p == 0.0 } // Filter out invalid lines

    println(s"Loaded ${data.length} records.")
    data
  }
}
