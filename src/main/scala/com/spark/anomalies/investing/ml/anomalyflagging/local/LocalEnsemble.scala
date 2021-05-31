package com.spark.anomalies.investing.ml.anomalyflagging.local

import com.spark.anomalies.investing.core.StockStreams
import com.spark.anomalies.investing.ml.anomalyflagging.Detector
import com.spark.anomalies.investing.ml.anomalyflagging.dto.DetectorOutput

import scala.collection.mutable.ListBuffer

class LocalEnsemble extends Detector {

  /**
   * Main function responsible for affecting the State Store if an anomaly is foind
   * @param stateStore value to be analysed
   * @return a Stock Stream with the current point identified as anomalous
   */
  override def anomalyFlagging(stateStore: StockStreams): StockStreams = {
    // ToDo
    stateStore
  }

  /**
   * Method to detect anomalies based on an ensemble of algorithms
   */
  private def detectAnomalies = {
    var results: ListBuffer[(String, DetectorOutput)] = ListBuffer[(String, DetectorOutput)]()

    ALGORITHMS.foreach{

    }
  }



}
