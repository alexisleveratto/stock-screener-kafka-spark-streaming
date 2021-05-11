package com.spark.anomalies.investing.ml.anomalyflagging.local.algorithms.array

import breeze.linalg.DenseVector
import com.spark.anomalies.investing.ml.anomalyflagging.dto.DetectorOutput

trait ArrayBasedDetector {

  protected val detectorName: String

  protected def fit(dv: DenseVector[Double]): DenseVector[Double]

  def anomalyScoring(dv: DenseVector[Double]): DetectorOutput

  def getDetectorName(): String = detectorName



}
