package com.spark.anomalies.investing.ml.anomalyflagging.local.algorithms.statistical

import breeze.linalg._
import breeze.numerics.abs
import breeze.stats.{stddev, mean => Bmean}
import com.spark.anomalies.investing.ml.anomalyflagging.dto.DetectorOutput

/** Class that implements Standard Deviation from the Average for Anomaly Detection
 * @param cutoff cutoff value that is the bases of the evaluation
 */
class StandardDeviationAverage(cutoff: Double) extends StatisticalDetector {

  override protected val detectorName: String = "StandardDeviationAverage"

  /** Method that returns the mean and standard deviation of a time-series
   * @param dv a dense vector
   * @return the mean and standard deviation of the input vector
   */
  override protected def fit(dv: DenseVector[Double]): (Double, Double) = {
    val mean = Bmean(dv(0 to -2))
    val std = stddev(dv(0 to -2))

    (mean, std)

  }

  /** Method to score anomalies based on their position against a threshold defined by the standard deviation
   * @param dv a dense vector
   * @return a boolean indicating if it is an anomaly and a double with the statistic
   */
  override def anomalyScoring(dv: DenseVector[Double]): DetectorOutput = {
    val (mean, std) = fit(dv)
    val statistic: Double = abs(dv(-1) - mean)
    val cut = std *:* cutoff

    DetectorOutput(statistic > cut, getDetectorName())
  }

}
