package com.spark.anomalies.investing.kafka

import yahoofinance.{Stock, YahooFinance}

object TEST extends App {

  val stock: Stock = YahooFinance.get("TSLA")

  val price: BigDecimal  = stock.getQuote().getPrice()
  val change: BigDecimal  = stock.getQuote().getChangeInPercent()
  val peg: BigDecimal  = stock.getStats().getPeg()
  val dividend: BigDecimal  = stock.getDividend().getAnnualYieldPercent()

  stock.print()
}
