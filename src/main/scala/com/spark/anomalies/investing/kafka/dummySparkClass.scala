package com.spark.anomalies.investing.kafka

import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

class dummySparkClass {

  def main(args: Array[String]): Unit = {
    val sparkSession = SparkSession.builder()
      .appName("Dummy")
      .master(args(0))
      .getOrCreate()

    val inputFile = args(1)
    val outputFile = args(2)

    val input = sparkSession.sparkContext.textFile(inputFile)

    val words = input.flatMap(line => line.split(" "))

    val counts = words.map(word => (word, 1)).reduceByKey {case (x, y) => x + y}

    counts.saveAsTextFile(outputFile)

  }

}
