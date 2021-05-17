package com.spark.anomalies.investing.ml.anomalyflagging.local.algorithms.statistical

import breeze.linalg._
import com.spark.anomalies.investing.ml.anomalyflagging.dto.DetectorOutput
import org.apache.commons.math3.stat.StatUtils.percentile

/**
 * Class that implements Tukey for Anomaly detection
 *
 * @param multiplier a factor to multiply the band by. The higher it is, the fewer anomalies will be flagged
 */
class Tukey(multiplier: Double) extends StatisticalDetector {
  override protected val detectorName: String = "Tukey"

  /**
   * Method to calculate statistics for evaluation
   * @param dv a dense vector
   * @return the first and third quantile of the input vector
   */
  override protected def fit(dv: DenseVector[Double]): (Double, Double) = {
    val p1 = percentile(dv(0 to -2).toArray, 25)
    val p3 = percentile(dv(0 to -2).toArray, 75)

    (p1, p3)
  }

  /**
   * Method to score anomalies based on their position against the identified bands
   * @param dv a dense vector
   * @return a boolean indicating if it's an anomaly and a string with the detector name
   */
  override def anomalyScoring(dv: DenseVector[Double]): DetectorOutput = {
    val (p1, p3) = fit(dv)
    val low = p1 - multiplier * (p3 - p1)
    val high = p3 + multiplier * (p3 - p1)

    DetectorOutput(dv(-1) > high || dv(-1) < low, getDetectorName())
  }


}
