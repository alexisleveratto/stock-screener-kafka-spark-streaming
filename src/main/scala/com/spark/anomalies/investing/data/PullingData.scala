package com.spark.anomalies.investing.data

import java.io.{BufferedReader, InputStreamReader}
import java.net.URL

object PullingData extends App {
  val cocaTicker: String = "KO"
  val url: URL = new URL("https://www.google.com/finance/quote/KO:NYSE")

  val inStream: InputStreamReader = new InputStreamReader(url.openStream())
  val buff: BufferedReader = new BufferedReader(inStream)

  var pageLine: String = buff.readLine()
  var price: String = "NOT FOUND"

  while (pageLine != null) {
    if (pageLine.contains("data-last-price")) {
      println(pageLine)
      val paramIndex = pageLine.indexOf("data-last-price")
      val decimal = pageLine.indexOf(".", paramIndex)
      var start = decimal
      while (pageLine.charAt(start) != '\"'){
        start = start - 1
      }
      price = pageLine.substring(start + 1, decimal + 3)
    }
    pageLine = buff.readLine()
  }

  println(price)



}
