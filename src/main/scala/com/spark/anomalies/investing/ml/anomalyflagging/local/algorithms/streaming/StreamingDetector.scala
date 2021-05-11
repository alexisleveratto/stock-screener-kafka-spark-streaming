package com.spark.anomalies.investing.ml.anomalyflagging.local.algorithms.streaming

import com.spark.anomalies.investing.ml.anomalyflagging.dto.DetectorOutput

trait StreamingDetector {
  protected val detectorName: String

  def smooth(dv: Seq[Double]): (Seq[Double], Seq[Double], Seq[Int])

  def anomalyScoring(dv: Seq[Double]): DetectorOutput

  /** Method that returns this detector name
   * @return detector name
   */
  def getDetectorName(): String = detectorName
}
