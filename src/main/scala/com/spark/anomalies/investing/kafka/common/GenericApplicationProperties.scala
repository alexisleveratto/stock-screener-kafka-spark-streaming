package com.spark.anomalies.investing.kafka.common

import com.typesafe.config.{Config, ConfigFactory}

import java.util.Properties
import java.util.logging.Logger

class GenericApplicationProperties(env: String) {
  val logger: Logger = Logger.getLogger(classOf[GenericApplicationProperties].getName)
  val configEnv: Config = ConfigFactory.load().getConfig(env)
  val configGlobal: Config = ConfigFactory.load().getConfig("global")
  val properties: Properties = new Properties()
  val stringDefaultValue: String = ""

  def getString(name: String) = {
    if (!properties.containsKey(name)) {
      putString(name)
    }
    properties.getProperty(name)
  }

  def putString(name: String) = {
    if (configEnv.hasPath(name) || configGlobal.hasPath(name)) {
      val i = if (configEnv.hasPath(name)) {
        configEnv.getString(name)
      } else {
        configGlobal.getString(name)
      }
      properties.put(name, String.valueOf(i))
    } else {
      properties.put(name, stringDefaultValue)
    }
  }

}

object GenericApplicationProperties extends Serializable {
  def apply(env: String): GenericApplicationProperties = new GenericApplicationProperties(env)
}
