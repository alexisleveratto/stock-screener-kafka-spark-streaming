package com.spark.anomalies.investing.kafka.producer

import com.fasterxml.jackson.databind.ser.std.NumberSerializers.LongSerializer
import com.spark.anomalies.investing.kafka.dto.StockPrice
import com.spark.anomalies.investing.kafka.serialization.JsonConverter
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}
import org.apache.kafka.common.serialization.StringSerializer

import java.util.Properties;

class StockProducer {

  val BOOTSTRAP_URL = "localhost:9092"
  val TOPIC_NAME = "stock-price"

  val kafkaProducerProperties: Properties = {
    val props = new Properties()
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_URL)
    props.put(ProducerConfig.CLIENT_ID_CONFIG, "stock-price-producer")
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, classOf[LongSerializer].getName)
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer].getName)
    props
  }

  val producer = new KafkaProducer[Long, String](kafkaProducerProperties)

  def send(stock: StockPrice) = {
    val msg = JsonConverter.toJson(stock)
    val record = new ProducerRecord[Long, String](TOPIC_NAME, System.currentTimeMillis(), msg)

    producer.send(record)
  }

  def close() = {
    producer.flush()
    producer.close()
  }

}

object StockProducer {
  def apply(): StockProducer = new StockProducer()
}
