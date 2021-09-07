package com.spark.anomalies.investing.kafka.config

import scopt.OptionParser

trait Parser {
  val parser: OptionParser[Config] = new OptionParser[Config]("StockStreamer") {
    opt[String]("nconsumers")
      .optional()
      .action((value, conf) => conf.copy(nconsumers = value.toInt))
      .validate(value =>
        if(value.isEmpty) failure("Number of consumers must be provided")
        else success
      )
  }
}
