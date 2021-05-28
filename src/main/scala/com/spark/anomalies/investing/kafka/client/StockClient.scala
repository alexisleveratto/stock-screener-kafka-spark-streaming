package com.spark.anomalies.investing.kafka.client

import yahoofinance.{Stock, YahooFinance}

class StockClient {

  def getHistoricalData(symbols: Array[String]): Array[Stock]= {
    symbols.map(
      x => YahooFinance.get(x)
    )
  }
}
