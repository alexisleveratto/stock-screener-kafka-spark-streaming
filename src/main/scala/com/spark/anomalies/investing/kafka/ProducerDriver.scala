package com.spark.anomalies.investing.kafka

import com.spark.anomalies.investing.kafka.common.GenericApplicationProperties
import com.spark.anomalies.investing.kafka.dto.StockPrice
import com.spark.anomalies.investing.kafka.producer.StockProducer

import java.util

class ProducerDriver extends SparkRun {

  override def execute(applicationProperties: GenericApplicationProperties): Unit = {
    val stocks = List("APPL", "TWT") // ToDo : move this to app properties

    val numberOfProducers = 1000 // ToDo : move this to app properties

    val stockProducer = new StockProducer(applicationProperties)

    stocks.foreach( // ToDo : Instead of one stock for each ticker make a timeframe
      ticker => {
        val stock = new StockPrice(ticker, 1000) // ToDo get price from yahoo finance
        stockProducer.send(stock)
      }
    )
    stockProducer.close()
  }

}
