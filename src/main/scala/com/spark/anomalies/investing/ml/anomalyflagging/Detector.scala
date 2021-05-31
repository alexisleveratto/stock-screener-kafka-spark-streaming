package com.spark.anomalies.investing.ml.anomalyflagging

import com.spark.anomalies.investing.core.StockStreams

trait Detector extends Serializable {

  def anomalyFlagging(stateStore: StockStreams): StockStreams

}
