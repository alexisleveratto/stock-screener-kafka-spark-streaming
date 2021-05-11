package com.spark.anomalies.investing.ml.anomalyflagging.dto


case class DetectorOutput(
                         var isAnomaly: Boolean,
                         var detectorName: String
                         )
