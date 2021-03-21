name := "Scraper"

version := "0.1"

scalaVersion := "2.13.5"

libraryDependencies ++= List(
  "org.jsoup" % "jsoup" % "1.8.3",
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2"
)
