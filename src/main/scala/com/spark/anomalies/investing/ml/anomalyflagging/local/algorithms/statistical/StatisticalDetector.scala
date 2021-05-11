package com.spark.anomalies.investing.ml.anomalyflagging.local.algorithms.statistical

import breeze.linalg.DenseVector
import com.spark.anomalies.investing.ml.anomalyflagging.dto.DetectorOutput

trait StatisticalDetector {

  protected val detectorName: String

  protected def fit(dv: DenseVector[Double]): (Double, Double)

  def anomalyScoring(dv: DenseVector[Double]): DetectorOutput

  def getDetectorName(): String = detectorName

}
