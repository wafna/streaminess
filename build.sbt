ThisBuild / scalaVersion := "2.13.6"

ThisBuild / scalaVersion := "2.13.6"
ThisBuild / organization := "wafna"

ThisBuild / run / fork := true
ThisBuild / test / run / fork := true

val typesafeVersion = "1.4.1"

val LogbackVersion = "1.2.5"
val Slf4jVersion = "1.7.32"

val akkaVersion = "2.6.15"
val akkaHttpVersion = "10.2.6"

val scalaTestVersion = "3.2.9"
val dockerTestKitVersion = "0.9.9"

val slf4jApi = "org.slf4j" % "slf4j-api" % Slf4jVersion
val logbackClassic = "ch.qos.logback" % "logback-classic" % LogbackVersion

val typesafeConfig = "com.typesafe" % "config" % typesafeVersion

val akkaActorTyped = "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion
val akkaStream = "com.typesafe.akka" %% "akka-stream" % akkaVersion
val akkaHttp = "com.typesafe.akka" %% "akka-http" % akkaHttpVersion
val akkaSprayJson = "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion

val scalaTest = "org.scalatest" %% "scalatest" % scalaTestVersion
val akkaActorTestKitTyped = "com.typesafe.akka" %% "akka-actor-testkit-typed" % akkaVersion


lazy val root: Project = project
  .in(file("."))
  .settings(
    libraryDependencies ++= Seq(
      slf4jApi,
      logbackClassic,
      typesafeConfig,
      scalaTest % Test
    )
  )

