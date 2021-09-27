package com.spark.anomalies.investing.kafka.producer

import com.fasterxml.jackson.databind.ser.std.NumberSerializers.LongSerializer
import com.spark.anomalies.investing.kafka.common.GenericApplicationProperties
import com.spark.anomalies.investing.kafka.constants.AppProperties
import com.spark.anomalies.investing.kafka.dto.StockPrice
import com.spark.anomalies.investing.kafka.serialization.JsonConverter
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord, RecordMetadata}
import org.apache.kafka.common.serialization.StringSerializer
import org.apache.log4j.Logger

import java.util.Properties
import java.util.concurrent.Future;

class StockProducer(appProperties: GenericApplicationProperties) {
  val logger: Logger = Logger.getLogger(classOf[StockProducer].getName)

  val TOPIC_NAME = "stock-price"

  logger.info(s"Defining Kafka Producer Properties")

  val kafkaProducerProperties: Properties = {
    val props = new Properties()
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, appProperties.getString(AppProperties.BOOTSTRAP_SERVER_CONFIG.toString))
    props.put(ProducerConfig.CLIENT_ID_CONFIG, "stock-price-producer")
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, classOf[LongSerializer].getName)
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer].getName)
    props
  }
  logger.info(s"Kafka Producer Properties ${kafkaProducerProperties}")

  logger.info(s"Creating producer")
  val producer = new KafkaProducer[Long, String](kafkaProducerProperties)
  logger.info(s"Producer created : ${producer}")


  def send(stock: StockPrice): Future[RecordMetadata] = {
    val msg = JsonConverter.toJson(stock)
    val record = new ProducerRecord[Long, String](appProperties.getString(AppProperties.KAFKA_STOCK_TOPICS.toString), System.currentTimeMillis(), msg)

    producer.send(record)
  }

  def close(): Unit = {
    producer.flush()
    producer.close()
  }

}

object StockProducer {
  def apply(appProperties: GenericApplicationProperties): StockProducer = new StockProducer(appProperties)
}
