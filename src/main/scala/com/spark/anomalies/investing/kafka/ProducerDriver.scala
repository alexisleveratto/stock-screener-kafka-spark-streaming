package com.spark.anomalies.investing.kafka

import com.spark.anomalies.investing.kafka.common.GenericApplicationProperties
import com.spark.anomalies.investing.kafka.dto.StockPrice
import com.spark.anomalies.investing.kafka.producer.StockProducer
import com.typesafe.scalalogging.LazyLogging

object ProducerDriver extends SparkRun with LazyLogging {

  override def execute(applicationProperties: GenericApplicationProperties): Unit = {

    val stocks = List("APPL") // ToDo : move this to app properties

    val stockProducer = new StockProducer(applicationProperties)

    logger.info("Sending Messages")
    stocks.foreach( // ToDo : Instead of one stock for each ticker make a timeframe
      ticker => {
        val stock = new StockPrice(ticker, 1000) // ToDo get price from yahoo finance
        stockProducer.send(stock)
      }
    )
    stockProducer.close()

  }

}
