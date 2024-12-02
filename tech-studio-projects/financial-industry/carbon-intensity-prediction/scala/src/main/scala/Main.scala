object Main extends App {
  val forecaster = new EnergyPriceForecaster()

  // Train the model
  forecaster.trainModel()

  // Make a prediction
  val predictedPrice = forecaster.predict(10.5)
  println(s"Predicted energy price: $$${predictedPrice}")
}
