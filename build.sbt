import Dependencies._
import Helpers._
import Resolvers._

lazy val root = (project in file ("."))
  .enablePlugins(BasicSettings, SiteSettingsPlugin)
  .settings(Publish.settings: _*)
  .settings(Release.settings: _*)
  .settings(resolvers += sonatypeRelease)
  .settings(
    name := "ScalaFX Utils",

    libraryDependencies ++= Seq (
      log4s,
      slf4j,
      jclBridge   % "runtime,optional",
      log4jBridge % "runtime,optional",
      logback     % "runtime,optional",
      gerweckUtil,
      scalaJava8,
      scalaFx,
      cats,
      shapeless
    ),

    /* Akka dependencies */
    libraryDependencies ++= Seq (
      akkaActor       % "optional",
      akkaStream      % "optional",
      gerweckUtilAkka % "optional"
    )
  )
