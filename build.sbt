name := "akka-scala-seed"

version := "1.0"

scalaVersion := "2.13.10"

lazy val akkaVersion = "2.7.0"

// Run in a separate JVM, to make sure sbt waits until all threads have
// finished before returning.
// If you want to keep the application running while executing other
// sbt tasks, consider https://github.com/spray/sbt-revolver/
fork := true

val AkkaVersion = "2.6.18"
val AkkaHttpVersion = "10.2.7"


libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor-typed" % "2.6.19",
  "com.typesafe.akka" %% "akka-testkit" % "2.6.19" % Test,
  "com.typesafe.akka" %% "akka-stream" % "2.6.19",
  "com.typesafe.akka" %% "akka-stream-typed" % "2.6.19",
  "org.scalameta" %% "munit" % "0.7.29" % Test,
  "ch.qos.logback" % "logback-classic" % "1.4.1" % Runtime,
)

lazy val root = (project in file("."))
  .settings(
    name := "Task3PTR"
  )