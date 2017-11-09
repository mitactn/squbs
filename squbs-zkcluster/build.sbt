import Versions._

name := "squbs-zkcluster"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaV,
  "com.typesafe.akka" %% "akka-remote" % akkaV,
  "com.typesafe.akka" %% "akka-slf4j" % akkaV,
  "org.apache.curator" % "curator-recipes" % "3.0.0" exclude("log4j", "log4j"),
  "org.apache.curator" % "curator-framework" % "3.0.0" exclude("org.jboss.netty", "netty") exclude("log4j", "log4j"),
  "com.typesafe.scala-logging" %% "scala-logging" % scalaLoggingV,
  "com.typesafe.akka" %% "akka-testkit" % akkaV % "test",
  "org.scalatest" %% "scalatest" % scalatestV % "test",
  "org.mockito" % "mockito-core" % "1.9.5" % "test",
  "org.apache.curator" % "curator-test" % "3.0.0" % "test" exclude("log4j", "log4j"),
  "ch.qos.logback" % "logback-classic" % "1.1.3" % "test"
)

(testOptions in Test) += Tests.Argument(TestFrameworks.ScalaTest, "-h", "report/squbs-zkcluster")

parallelExecution := false

cleanFiles += baseDirectory.value / "zookeeper"

updateOptions := updateOptions.value.withCachedResolution(true)
