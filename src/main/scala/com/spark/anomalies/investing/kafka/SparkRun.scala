package com.spark.anomalies.investing.kafka

import com.spark.anomalies.investing.kafka.common.GenericApplicationProperties
import org.apache.spark.sql.SparkSession

trait SparkRun {
  def main(args: Array[String]): Unit = {
    val appProperties = new GenericApplicationProperties("global")

    // Start Spark Session
    val spark = SparkSession
      .builder
      .appName("StockStreamerApp")
      .master(args(0))
      .getOrCreate()

    // Set timezone
    spark.conf.set("spark.sql.session.timeZone", "UTC")

    // Enable streaming metrics and set log level
    spark.conf.set("spark.sql.streaming.metricsEnabled", value=true)
    spark.sparkContext.setLogLevel("INFO")

    execute(appProperties)
  }

  def execute(applicationProperties: GenericApplicationProperties)
}
