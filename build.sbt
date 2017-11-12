

scalaVersion in ThisBuild := "2.12.3"

organization in ThisBuild := "org.squbs"

publishArtifact := false

fork in ThisBuild := true

parallelExecution in ThisBuild := false

lazy val `squbs-pipeline` = project

lazy val `squbs-unicomplex` = project dependsOn (`squbs-pipeline`, `squbs-ext`)

lazy val `squbs-testkit` = (project dependsOn `squbs-unicomplex`).enablePlugins(de.johoop.testngplugin.TestNGPlugin)

lazy val `squbs-zkcluster` = project dependsOn `squbs-testkit` % "test"

lazy val `squbs-httpclient` = project dependsOn(`squbs-ext` % "compile->compile;test->test",
  `squbs-pipeline`, `squbs-testkit` % "test")

// Add SlowTest configuration to squbs-pattern to run the long-running tests.
// To run standard tests> test
// To run slow tests including all stress tests> slow:test
lazy val SlowTest = config("slow") extend Test

// Setup squbs-pattern with slow tests enabled.
// Perhaps we can do it better in future by hiding the details in the plugin.
lazy val `squbs-pattern` = (project dependsOn (`squbs-ext`, `squbs-testkit` % "test"))
  .configs(SlowTest)
  .settings(inConfig(SlowTest)(Defaults.testTasks): _*)
  .settings(testOptions in SlowTest := Seq.empty)

lazy val `squbs-actorregistry` = project dependsOn (`squbs-unicomplex`, `squbs-testkit` % "test")

lazy val `squbs-actormonitor` = project dependsOn (`squbs-unicomplex`, `squbs-testkit` % "test")

lazy val `squbs-admin` = project dependsOn (`squbs-unicomplex`, `squbs-testkit` % "test")

lazy val `squbs-ext` = project dependsOn `squbs-pipeline` % "provided"

credentials += Credentials("Sonatype Nexus Repository Manager", "n.xchanger.cn", "admin", "admin123")

publishTo in ThisBuild := {
  val nexus = "http://n.xchanger.cn/nexus/content/repositories/"
  if (version.value.trim.endsWith("SNAPSHOT"))
    Some("snapshots" at nexus + "snapshots")
  else
    Some("releases" at nexus + "releases")
}

publishMavenStyle in ThisBuild := true

publishArtifact in Test := false

checksums in publishLocal := Nil

checksums in publish := Nil