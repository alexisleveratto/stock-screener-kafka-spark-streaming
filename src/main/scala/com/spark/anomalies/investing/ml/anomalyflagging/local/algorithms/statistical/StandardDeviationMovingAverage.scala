package com.spark.anomalies.investing.ml.anomalyflagging.local.algorithms.statistical

import breeze.linalg._
import breeze.numerics.abs
import org.apache.commons.math3.stat.StatUtils.{mean, populationVariance}

import com.spark.anomalies.investing.ml.anomalyflagging.dto.DetectorOutput


/**
 * Class that implements Exponential Moving Average for anomaly detection
 *
 * @param window Number of points to consider in the evaluation
 * @param cutoff cutoff value that is the basis of the evaluation
 * @param alpha wighting factor
 */
class StandardDeviationMovingAverage(window: Int, cutoff: Double, alpha: Option[Double]) extends StatisticalDetector {
  override protected val detectorName: String = "StandardDeviationMovingAverage"

  /**
   * Method that returns the mean and standard deviation of a time-series
   * @param dv a dense vector
   * @return the mean and standard deviation of the input vector
   */
  override protected def fit(dv: DenseVector[Double]): (Double, Double) = {
    val k: Double = alpha.getOrElse(2.0 / (window + 1))

    val rollingMean = dv.toArray.takeRight(window).foldLeft(sma(window, dv.toArray))((last, s) => (1 - k) * last + k * s)

    val rollingStddev = dv.toArray.takeRight(window).foldLeft(sms(window, dv.toArray))((last, s) => (1 - k) * last + k * s)

    (rollingMean, rollingStddev)
  }

  /**
   * Method to score anomalies based on their position against the identified bands
   * @param dv a dense vector
   * @return a boolean indicating if it's an anomaly and a string with the detector name
   */
  override def anomalyScoring(dv: DenseVector[Double]): DetectorOutput = {
    val (mean, std) = fit(dv)
    val statistic = abs(dv(-1) - mean)
    val cut = std *:* cutoff

    DetectorOutput(statistic > cut, getDetectorName())
  }

  private def sma(days: Int, ls: Array[Double]): Double = {
    mean(ls.takeRight(days))
  }

  private def sms(days: Int, ls: Array[Double]): Double = {
    Math.sqrt(populationVariance(ls.takeRight(days)))
  }

}
