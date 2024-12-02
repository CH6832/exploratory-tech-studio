name := "RenewableEnergyForecasting"

version := "0.1"

scalaVersion := "2.13.12"  // Scala version compatible with Spark

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "3.3.2",  // Spark Core library
  "org.apache.spark" %% "spark-mllib" % "3.3.2",  // Spark MLlib for machine learning
"org.apache.commons" % "commons-csv" % "1.10.0"
)

resolvers += Resolver.mavenCentral  // Add Maven Central repository
