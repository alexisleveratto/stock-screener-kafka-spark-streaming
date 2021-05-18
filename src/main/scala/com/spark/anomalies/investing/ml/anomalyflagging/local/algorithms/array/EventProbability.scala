package com.spark.anomalies.investing.ml.anomalyflagging.local.algorithms.array

import breeze.linalg._
import com.spark.anomalies.investing.ml.anomalyflagging.dto.DetectorOutput

import java.util.function.DoubleBinaryOperator

/**
 * Class that implements Median Absolute Deviation for anomaly detection
 *
 * @param cutoffMultiplier multiplier that increments cutoff value, the higher it is, the fewer anomalies it flags
 */
class EventProbability(cutoffMultiplier: Int = 1) extends ArrayBasedDetector {
  override protected val detectorName: String = "EventProbability"

  /**
   * Method that returns a vector of frequencies to be used in evaluation
   * @param dv a dense vector
   * @return a frequency vector
   */
  override protected def fit(dv: DenseVector[Double]): DenseVector[Double] = {
    val analysisArray = dv.toArray
    val total = analysisArray.length

    val frequencyVector = analysisArray.map(value => (value, analysisArray.count(_ < value)))
    val surpriseScore: Array[Double] = frequencyVector.map(x => expectationLevel(probabilityOfEvent(x._2, total)))

    DenseVector(surpriseScore: _*)
  }

  override def anomalyScoring(dv: DenseVector[Double]): DetectorOutput = {
    val surpriseScores = fit(dv)

    DetectorOutput(surpriseScores(-1) > cutoffMultiplier * max(surpriseScores(0 to -2)), detectorName)
  }

  private def probabilityOfEvent(count: Long, total: Long): Double = {
    count / total.toDouble
  }

  /** Function that returns the level of expectation of a given value
   *
   * @param prob probability of the value
   * @return degree of expectation of that value
   */
  private def expectationLevel(prob: Double): Double = {
    if (prob > 0.0){
      -1 * log2(prob)
    } else {
      -1 * log2(0.01)
    }
  }

  /** Auxiliary function to calculate log of a value
   *
   * @param value value to be logged
   * @return the logged value
   */
  private def log2(value: Double): Double ={
    val l0g2 = scala.math.log(2)

    if (!scala.math.log(value).isInfinite){
      scala.math.log(value) /l0g2
    } else {
      0.01 / l0g2
    }
  }

}
