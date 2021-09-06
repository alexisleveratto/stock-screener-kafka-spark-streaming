package com.spark.anomalies.investing.kafka.consumer

import com.fasterxml.jackson.databind.deser.std.NumberDeserializers.LongDeserializer
import com.spark.anomalies.investing.kafka.common.GenericApplicationProperties
import com.spark.anomalies.investing.kafka.constants.AppProperties
import org.apache.kafka.clients.consumer.{ConsumerConfig, KafkaConsumer}
import org.apache.kafka.common.errors.WakeupException
import org.apache.kafka.common.serialization.StringDeserializer

import java.time.Duration
import java.util
import java.util.Properties
import java.util.logging.Logger

class StockConsumer(id: String, appProperties: GenericApplicationProperties) extends Runnable {

  val GROUP_ID: String = s"stock-price-consumer-$id"
  val topics: util.Collection[String] = appProperties.getString(AppProperties.KAFKA_STOCK_TOPICS.toString).split(",")
  val logger: Logger = Logger.getLogger(classOf[StockConsumer].getName)

  val kafkaConsumerProperties: Properties = {
    val props = new Properties()
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, appProperties.getString(AppProperties.BOOTSTRAP_SERVER_CONFIG.toString))
    props.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID)
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, classOf[LongDeserializer].getName)
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, classOf[StringDeserializer].getName)
    props
  }

  val consumer = new KafkaConsumer[Long, String](kafkaConsumerProperties)

  def shutdown(): Unit = {
    consumer.wakeup()
  }

  override def run(): Unit = {
    try {
      consumer.subscribe(topics)
      logger.info(s"{$id} - subscribed to {$topics}")
      while (true) {
        // ToDo : CHANGE while(true){...} for a timeout
        var stockPrices = consumer.poll(Duration.ofSeconds(10)) // ToDo : this seconds should be in the properties file
        stockPrices.forEach(
          s => logger.info(
            Thread.currentThread().getName +
              s" : id $id, offset=${s.offset()}, key=${s.key()}, value=${s.value()}  timestamp= ${s.timestamp()}"
          ))
      }
    } catch {
      case e: WakeupException => e.printStackTrace()
    } finally {
      consumer.close()
    }
  }

}

object StockConsumer {
  def apply(id: String, appProperties: GenericApplicationProperties): StockConsumer = new StockConsumer(id, appProperties)
}
