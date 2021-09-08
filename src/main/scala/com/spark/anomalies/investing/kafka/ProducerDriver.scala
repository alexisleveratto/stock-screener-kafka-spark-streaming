package com.spark.anomalies.investing.kafka

import com.spark.anomalies.investing.kafka.common.GenericApplicationProperties
import com.spark.anomalies.investing.kafka.dto.StockPrice
import com.spark.anomalies.investing.kafka.producer.StockProducer

import java.util

class ProducerDriver {

  def execute(applicationProperties: GenericApplicationProperties): Unit = {
    val stocks = List("APPL", "TWT") // ToDo : move this to app properties
    val numerOfProducers = 1000 // ToDo : move this to app properties

    val stockProducer = new StockProducer()

    stocks.foreach(
      ticker => {
        val stock = new StockPrice(ticker, 1000) // ToDo get price from yahoo finance
        stockProducer.send(stock)
      }
    )
    stockProducer.close()
  }

}