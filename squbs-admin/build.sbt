import Versions._

name := "squbs-admin"

Revolver.settings

javaOptions in Test += "-Xmx512m"

libraryDependencies ++= Seq(
  "org.scala-lang" % "scala-reflect" % scalaVersion.value,
  "org.scalatest" %% "scalatest" % scalatestV % "test",
  "com.typesafe.akka" %% "akka-actor" % akkaV,
  "com.typesafe.akka" %% "akka-agent" % akkaV,
  "com.typesafe.akka" %% "akka-http" % akkaHttpV excludeAll( ExclusionRule(organization = "com.typesafe.akka")),
  "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpV % "test",
  "com.typesafe.scala-logging" %% "scala-logging" % scalaLoggingV,
  "com.typesafe.akka" %% "akka-testkit" % akkaV % "test",
  "ch.qos.logback" % "logback-classic" % "1.1.3" % "test",
  "org.json4s" %% "json4s-jackson" % json4sV
)

(testOptions in Test) += Tests.Argument(TestFrameworks.ScalaTest, "-h", "report/squbs-unicomplex")

mainClass in (Compile, run) := Some("org.squbs.unicomplex.Bootstrap")