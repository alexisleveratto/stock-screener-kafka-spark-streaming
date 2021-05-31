package com.spark.anomalies.investing.kafka.producer

import org.slf4j.{Logger, LoggerFactory}
import org.apache.kafka.clients.producer._
import org.apache.kafka.common.serialization.StringSerializer

import java.util.Properties
import java.util.concurrent.{BlockingQueue, LinkedBlockingQueue}

class StockProducer {
  private val bootstrapServer: String = "127.0.0.1:9092"

  val logger: Logger = LoggerFactory.getLogger(this.getClass.getName)

  val stocks: Array[String] = Array("AAPL")

  def run = {
    logger.info("Producer Setup")
    // Client
    /** Set up your blocking queues: Be sure to size these properly based on expected TPS of your stream */
    val msgQueue: BlockingQueue[String] = new LinkedBlockingQueue[String](1000)
  }

  def createKafkaProducer = {
    val properties: Properties = new Properties()
    // properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.)
  }



}
