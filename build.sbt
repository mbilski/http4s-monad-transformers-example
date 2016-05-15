name := "http4s-with-monad-transformers"

version := "1.0"

scalaVersion := "2.11.8"

val http4sVersion = "0.13.2"
val circeVersion = "0.3.0"

libraryDependencies ++= Seq(
  "org.http4s" %% "http4s-dsl",
  "org.http4s" %% "http4s-core",
  "org.http4s" %% "http4s-circe",
  "org.http4s" %% "http4s-blaze-server",
  "org.http4s" %% "http4s-blaze-client"
).map(_ % http4sVersion) ++ Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic"
).map(_ % circeVersion) ++ Seq(
  "org.scalatest" % "scalatest_2.11" % "2.2.6" % "test"
)
    