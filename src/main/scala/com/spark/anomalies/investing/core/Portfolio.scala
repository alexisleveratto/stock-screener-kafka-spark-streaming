package com.spark.anomalies.investing.core

import breeze.linalg.DenseVector

case class jjPortfolio(
                    val onHold: DenseVector[StockStreams],
                    val addThis: DenseVector[String],
                    val takeLook: Boolean
                    )
