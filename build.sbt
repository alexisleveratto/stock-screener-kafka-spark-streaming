ThisBuild / scalaVersion := "2.13.5"
ThisBuild / organization := "com.investing.spark"

lazy val invest = (project in file ("."))
  .settings(
    name := "investing-anomalies-detection",
    version := "0.1",
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.2.7" % Test,

      // Breeze
      "org.scalanlp" %% "breeze" % "1.2",
      "org.scalanlp" %% "breeze-viz" % "1.2",

      // Kafka
      "org.apache.kafka" % "kafka-clients" % "2.7.0",
      "io.confluent" % "kafka-avro-serializer" % "6.0.0",

      // https://github.com/lightbend/config
      "com.typesafe" % "config" % "1.4.1",

      // Yahoo Finance API TEST
      "com.yahoofinance-api" % "YahooFinanceAPI" % "3.15.0"
    )
  )


