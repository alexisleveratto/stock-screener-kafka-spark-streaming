ThisBuild / scalaVersion := "2.13.5"
ThisBuild / organization := "com.investing.spark"

lazy val invest = (project in file ("."))
  .settings(
    name := "investing-anomalies-detection",
    version := "0.1",
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.2.7" % Test,
      // Breeze - Last stable release [11.05.2021]
      "org.scalanlp" %% "breeze" % "1.2",
      // Breeze - The visualization library is distributed separately as well. It depends on LGPL code
      "org.scalanlp" %% "breeze-viz" % "1.2",
      // Kafka
      "org.apache.kafka" % "kafka-clients" % "0.11.0.0",
      "io.confluent" % "kafka-avro-serializer" % "6.0.0",
      // Yahoo Finance API TEST
      "com.yahoofinance-api" % "YahooFinanceAPI" % "3.15.0"
    )
  )


