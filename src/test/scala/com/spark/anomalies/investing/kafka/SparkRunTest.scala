package com.spark.anomalies.investing.kafka

import org.apache.spark.{SparkContext}
import org.apache.spark.sql.SparkSession
import org.scalatest.BeforeAndAfterAll
import org.scalatest.funsuite.AnyFunSuite
import com.spark.anomalies.investing.kafka.common.GenericApplicationProperties

trait SparkRunTest extends AnyFunSuite with BeforeAndAfterAll {
  protected var sparkContext: SparkContext = _
  protected var sparkSession: SparkSession = _

  protected var appProps: GenericApplicationProperties = _

  override def beforeAll(): Unit = {
    super.beforeAll()

    sparkSession = SparkSession.builder
      .appName(this.getClass.getSimpleName)
      .config("spark.master", "local")
      .config("spark.driver.host", "localhost")
      .getOrCreate()

    sparkContext = sparkSession.sparkContext

    appProps = new GenericApplicationProperties("global")

  }


}
