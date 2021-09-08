package com.spark.anomalies.investing.kafka

import com.spark.anomalies.investing.kafka.common.GenericApplicationProperties
import org.apache.spark.sql.SparkSession

trait SparkRun {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("Stock Streamer")
      .master(args(0))
      .getOrCreate()
    val appProperties = new GenericApplicationProperties("global")

    execute(appProperties)

  }

  def execute(applicationProperties: GenericApplicationProperties)
}
