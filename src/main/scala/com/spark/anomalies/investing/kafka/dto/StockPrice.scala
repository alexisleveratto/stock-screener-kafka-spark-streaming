package com.spark.anomalies.investing.kafka.dto

class StockPrice(ticker: String, price: BigDecimal) {

  override def toString: String = {
    s"StockPrice : [ticker={$ticker}, price={$price}]"
  }
}

object StockPrice extends Serializable {
  def apply(ticker: String, price: BigDecimal) = new StockPrice(ticker, price)
}
