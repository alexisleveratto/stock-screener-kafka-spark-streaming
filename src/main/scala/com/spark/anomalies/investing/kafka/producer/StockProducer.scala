package com.spark.anomalies.investing.kafka.producer

import com.fasterxml.jackson.databind.ser.std.NumberSerializers.LongSerializer
import com.spark.anomalies.investing.kafka.common.GenericApplicationProperties
import com.spark.anomalies.investing.kafka.constants.AppProperties
import com.spark.anomalies.investing.kafka.dto.StockPrice
import com.spark.anomalies.investing.kafka.serialization.JsonConverter
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}
import org.apache.kafka.common.serialization.StringSerializer

import java.util.Properties;

class StockProducer(appProperties: GenericApplicationProperties) {

  val TOPIC_NAME = "stock-price"

  val kafkaProducerProperties: Properties = {
    val props = new Properties()
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, appProperties.getString(AppProperties.BOOTSTRAP_SERVER_CONFIG.toString))
    props.put(ProducerConfig.CLIENT_ID_CONFIG, "stock-price-producer")
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, classOf[LongSerializer].getName)
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer].getName)
    props
  }

  val producer = new KafkaProducer[Long, String](kafkaProducerProperties)

  def send(stock: StockPrice) = {
    val msg = JsonConverter.toJson(stock)
    val record = new ProducerRecord[Long, String](appProperties.getString(AppProperties.KAFKA_STOCK_TOPICS.toString), System.currentTimeMillis(), msg)

    producer.send(record)
  }

  def close() = {
    producer.flush()
    producer.close()
  }

}

object StockProducer {
  def apply(appProperties: GenericApplicationProperties): StockProducer = new StockProducer(appProperties)
}
