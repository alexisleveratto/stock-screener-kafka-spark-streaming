package com.spark.anomalies.investing.kafka.serialization

import com.fasterxml.jackson.databind.ObjectMapper

object JsonConverter {
  val mapper: ObjectMapper = new ObjectMapper()

  def fromJson(src: String, clazz: Class[Any]) = {
    try {
      mapper.readValue(src, clazz)
    } catch {
      case exception: Exception => throw new RuntimeException(exception)
    }
  }

  def toJson(value: Object) = {
    try {
      mapper.writeValueAsString(value)
    } catch {
      case exception: Exception => throw new RuntimeException(exception)
    }
  }

}
