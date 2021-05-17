package com.spark.anomalies.investing.ml.anomalyflagging.local.algorithms.statistical

import breeze.linalg._
import breeze.numerics.abs
import breeze.stats.median
import com.spark.anomalies.investing.ml.anomalyflagging.dto.DetectorOutput

/** Class that implements Median Absolute Deviation for anomaly detection
 * @param cutoff cutoff value that is the basis of the evaluation
 */
class MedianAbsoluteDeviation(cutoff: Double) extends StatisticalDetector {

  override protected val detectorName: String = "MedianAbsoluteDeviation"

  /** Method that returns the median of the context and the respective median deviation
   * @param dv a dense vector
   * @return median and median deviation of the context vector
   */
  override protected def fit(dv: DenseVector[Double]): (Double, Double) = {
    val m: Double = breeze.stats.median(dv(0 to -2))
    val demedianed: DenseVector[Double] = abs(dv(0 to -2) - m)
    val medianDeviation: Double = median(demedianed)
    (m, medianDeviation)
  }

  /** Method to score the last element of a vector based on their position against the median deviation
   * @param dv a dense vector
   * @return a tuple containing a boolean and a statistic
   */
  override def anomalyScoring(dv: DenseVector[Double]): DetectorOutput = {
    val (median, medianDeviation) = fit(dv)
    val demdianedValue = dv(-1) - median
    val statistics: Double = 0.6745 * (demdianedValue/medianDeviation)
    DetectorOutput(statistics > cutoff, detectorName)
  }



}
