package com.spark.anomalies.investing.ml.anomalyflagging.local.algorithms.statistical

import breeze.linalg.DenseVector
import com.spark.anomalies.investing.ml.anomalyflagging.dto.DetectorOutput
import org.scalatest.BeforeAndAfterAll
import org.scalatest.funsuite.AnyFunSuite

class StandardDeviationAverageTest extends AnyFunSuite with BeforeAndAfterAll {

  var peak: DenseVector[Double] = _
  var drop: DenseVector[Double] = _
  var normal: DenseVector[Double] = _
  var cutoff: Double = _
  var standardDeviationMovingAverage: StandardDeviationMovingAverage = _
  var standardDeviationMovingAverageNone: StandardDeviationMovingAverage = _
  var detectorName: String = _
  var window: Int = _

  override def beforeAll(): Unit = {

    peak = DenseVector[Double](1, 11, 9, 12, 20, 8, 14, 12, 11, 10, 11, 200)
    drop = DenseVector[Double](200, 201, 200, 203, 206, 201, 202, 204, 203, 202, 201, 1)
    normal = DenseVector[Double](11, 11, 10, 10, 10, 12, 12, 13, 13, 14, 14, 12)

    cutoff = 1.0
    window = 7

    standardDeviationMovingAverage = new StandardDeviationMovingAverage(window, cutoff, Some(0.5))
    standardDeviationMovingAverageNone = new StandardDeviationMovingAverage(window, cutoff, None)
    detectorName = standardDeviationMovingAverageNone.getDetectorName()

  }
  test("Anomaly | Peak") {
    assert(standardDeviationMovingAverageNone.anomalyScoring(peak) == DetectorOutput(isAnomaly = true, detectorName))
  }

  test("Anomaly | Drop") {
    assert(standardDeviationMovingAverageNone.anomalyScoring(drop) == DetectorOutput(isAnomaly = true, detectorName))
  }
  test("No Anomaly | Normal") {
    assert(standardDeviationMovingAverageNone.anomalyScoring(normal) == DetectorOutput(isAnomaly = false, detectorName))
  }



}
