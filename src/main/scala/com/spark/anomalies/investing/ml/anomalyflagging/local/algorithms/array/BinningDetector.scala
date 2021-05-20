package com.spark.anomalies.investing.ml.anomalyflagging.local.algorithms.array

import breeze.linalg._
import breeze.numerics.abs
import com.spark.anomalies.investing.ml.anomalyflagging.dto.DetectorOutput

/**
 * Class that implements Median Absolute Deviation for anomaly detection
 *
 * @param cutoffMultiplier multiplier to increase cutoff, the higher it is the fewer anomalies are flagged
 * @param normalizerValue binning value to determine normalization
 */
class BinningDetector(cutoffMultiplier: Int = 1, normalizerValue: Int = 3) extends ArrayBasedDetector {
  override protected val detectorName: String = "binning"

  /**
   * Method that normalize a vector using contextual information
   * @param dv a dense vector
   * @return a normalized vector
   */
  override protected def fit(dv: DenseVector[Double]): DenseVector[Double] = {
    val context = dv(0 to -2)

    val min = breeze.linalg.min(context)
    val max = breeze.linalg.max(context)

    val range = if (max == min) 1 else max - min
    val maxBin = math.pow(2,normalizerValue) - 1

    val minS = range / maxBin

    dv.map(x => abs(x-min)/minS)

  }

  /** Method to score the last element of a vector based on their position against the reamining no
   * @param dv a dense vector
   * @return a tuple containing a boolean and a statistic
   */
  override def anomalyScoring(dv: DenseVector[Double]): DetectorOutput = {
    val scores = fit(dv)
    val evaluationContext = cutoffMultiplier * max(scores(0 to -2))

    DetectorOutput(scores(-1) > evaluationContext, detectorName)
  }
}
