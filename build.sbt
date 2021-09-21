ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "2.12.5"
ThisBuild / organization := "com.investing.spark"

ThisBuild / assemblyMergeStrategy := {
  case PathList("javax", "servlet", xs @ _*)         => MergeStrategy.first
  case PathList(ps @ _*) if ps.last endsWith ".html" => MergeStrategy.first
  case PathList(ps @ _*) if ps.last endsWith ".jar"  => MergeStrategy.first
  case "application.conf"                            => MergeStrategy.concat
  case "unwanted.txt"                                => MergeStrategy.discard
  case _                                             => MergeStrategy.last

}

lazy val invest = (project in file("."))
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
      // "io.confluent" % "kafka-avro-serializer" % "6.0.0", // confluent is making sbt fail

      // typesafe config - https://github.com/lightbend/config
      "com.typesafe" % "config" % "1.4.1",

      // Yahoo Finance API TEST
      "com.yahoofinance-api" % "YahooFinanceAPI" % "3.15.0",

      // log4j - https://logging.apache.org/log4j/2.x/maven-artifacts.html
      "org.apache.logging.log4j" % "log4j-api" % "2.14.1",
      "org.apache.logging.log4j" % "log4j-core" % "2.14.1",

      "org.elasticsearch" % "elasticsearch" % "2.3.5",

      // https://github.com/scopt/scopt
      "com.github.scopt" %% "scopt" % "4.0.0",

      // https://mvnrepository.com/artifact/org.apache.spark/spark-core
      "org.apache.spark" %% "spark-core" % "3.1.1",
      // https://mvnrepository.com/artifact/org.apache.spark/spark-sql
      "org.apache.spark" %% "spark-sql" % "3.1.1" % "provided"
    )
  )


