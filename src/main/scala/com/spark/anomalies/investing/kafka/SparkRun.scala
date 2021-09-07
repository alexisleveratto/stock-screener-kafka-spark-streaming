package com.spark.anomalies.investing.kafka

import com.spark.anomalies.investing.kafka.common.GenericApplicationProperties
import org.apache.spark.sql.SparkSession

trait SparkRun {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
  }

  def execute(applicationProperties: GenericApplicationProperties)
}
