package com.spark.anomalies.investing.kafka

import com.spark.anomalies.investing.kafka.common.GenericApplicationProperties

trait SparkRun {
  def main(args: Array[String]): Unit = {

  }

  def execute(applicationProperties: GenericApplicationProperties)
}
