package com.spark.anomalies.investing.kafka

class ProducerDriverTest extends SparkRunTest {

  test("Execute | Local master") {
    ProducerDriver.execute(appProps)
  }

}
