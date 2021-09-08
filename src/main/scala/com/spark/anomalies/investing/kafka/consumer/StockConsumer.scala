package com.spark.anomalies.investing.kafka.consumer

import com.fasterxml.jackson.databind.deser.std.NumberDeserializers.LongDeserializer
import com.spark.anomalies.investing.kafka.common.GenericApplicationProperties
import com.spark.anomalies.investing.kafka.constants.AppProperties
import org.apache.kafka.clients.consumer.{ConsumerConfig, KafkaConsumer}
import org.elasticsearch.action.bulk.BulkRequest
import org.elasticsearch.action.bulk.BulkResponse
import org.apache.kafka.common.serialization.StringDeserializer

import java.time.Duration
import java.util
import java.util.Properties
import java.util.logging.Logger

import collection.JavaConverters._

class StockConsumer(id: String, appProperties: GenericApplicationProperties) extends Runnable {

  val GROUP_ID: String = s"stock-price-consumer-$id"
  val topics = appProperties.getString(AppProperties.KAFKA_STOCK_TOPICS.toString).split(",").toSeq
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
    consumer.subscribe(topics.asJava)
    logger.info(s"$id subscribed to $topics")
    // ToDo : CHANGE while(true){...} for a timeout .  something like (System.currentTimeMillis() < endTime)
    while (true) {
      val records = consumer.poll(Duration.ofMillis(100))

      logger.info(s"Received ${records.count()} records")
      records.forEach(
        stock =>
          println(
            s"${Thread.currentThread().getName} : id=$id, offset=${stock.offset}, key=${stock.key()}, value=${stock.value()}, timestamp=${stock.timestamp()}"
          )
      )
      if (records.count() > 0) {
        logger.info("Committing offset ... ")
        consumer.commitSync()
        logger.info("Offset have been committed")
        try {
          Thread.sleep(1000)
        } catch {
          case e: InterruptedException => e.printStackTrace()
        }
      }

    }

  }

}

object StockConsumer {
  def apply(id: String, appProperties: GenericApplicationProperties): StockConsumer = new StockConsumer(id, appProperties)
}
