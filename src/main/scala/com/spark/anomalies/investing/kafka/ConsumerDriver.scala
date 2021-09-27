package com.spark.anomalies.investing.kafka
import com.spark.anomalies.investing.kafka.common.GenericApplicationProperties
import com.spark.anomalies.investing.kafka.consumer.StockConsumer

import java.util.UUID
import java.util.concurrent.Executors

object ConsumerDriver extends SparkRun {

  def execute(applicationProperties: GenericApplicationProperties): Unit = {
    val numberConsumers = 2 // ToDo add this to spark-submit or application properties

    val executors = Executors.newFixedThreadPool(numberConsumers)

    var consumers = new Array[StockConsumer](numberConsumers)

    val id = UUID.randomUUID().toString

    val consumer = new StockConsumer(id, applicationProperties)

    consumer.run()


  }

}
