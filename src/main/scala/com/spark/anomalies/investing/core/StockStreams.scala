package com.spark.anomalies.investing.core

import breeze.linalg.DenseVector

import java.sql.Timestamp

case class StockStreams(
                       val name: String,
                       val stockStreams: DenseVector[StockPoint],
                       val lastUpdate: Timestamp,
                       val invest: Boolean
                       )
