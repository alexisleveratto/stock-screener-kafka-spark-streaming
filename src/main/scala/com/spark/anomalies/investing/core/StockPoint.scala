package com.spark.anomalies.investing.core

import java.sql.Timestamp

case class StockPoint(
                       val ticker: String,
                       val high: Double,
                       val low: Double,
                       val open: Double,
                       val close: Double,
                       val timestamp: Timestamp,
                       val anomaly: Boolean
                      )
