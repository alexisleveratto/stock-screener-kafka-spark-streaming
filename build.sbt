ThisBuild / scalaVersion := "2.13.5"
ThisBuild / organization := "com.investing.spark"

lazy val invest = (project in file ("."))
  .settings(
    name := "investing-anomalies-detection",
    version := "0.1",
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.2.7" % Test,
      // Breeze - Last stable release [11.05.2021]
      "org.scalanlp" %% "breeze" % "1.2",
      // Breeze - The visualization library is distributed separately as well. It depends on LGPL code
      "org.scalanlp" %% "breeze-viz" % "1.2"

    )
  )


