package com.spark.anomalies.investing.core

import java.sql.Timestamp

case class StockPoint(
                       val ticker: String,
                       val timestamp: Timestamp,
                       val open: Double,
                       val high: Double,
                       val low: Double,
                       val close: Double,
                       val adjClose: Double,
                       val volume: Double,
                       val anomaly: Boolean = false
                      )
