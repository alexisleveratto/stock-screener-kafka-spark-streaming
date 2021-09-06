package com.spark.anomalies.investing.kafka
import com.spark.anomalies.investing.kafka.common.GenericApplicationProperties
import com.spark.anomalies.investing.kafka.consumer.StockConsumer

import java.util.concurrent.Executors

class ConsumerDriver extends SparkRun {

  override def execute(applicationProperties: GenericApplicationProperties): Unit = {
    val numberConsumers = 2 // ToDo add this to spark-submit
    val executors = Executors.newFixedThreadPool(numberConsumers)

    var consumers = new Array[StockConsumer](numberConsumers)

  }

}
