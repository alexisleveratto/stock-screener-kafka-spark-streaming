package com.spark.anomalies.investing.ml.anomalyflagging.constants

object Algorithms extends Enumeration {

  val stdAvg = "stdAvg"
  val mad = "mad"
  val eventProbability = "eventProbability"
  val binning = "binning"
  val tukey = "tukey"
  val stdMovAvg = "stdMovAvg"
  val zscore = "zscore"

}
