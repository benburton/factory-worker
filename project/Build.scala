import sbt._
import sbt.Keys._
import scala.sys.process.Process

object Build extends sbt.Build {
  
  val baseVersion = "0.1"
  lazy val libVersion = s"""${baseVersion}-${Process("git rev-parse --short HEAD").lines.head}"""

  lazy val main = Project(
    id = "factory-worker",
    base = file("."),
    settings = Project.defaultSettings ++ Seq(
      name := "factory-worker",
      organization := "com.benburton",
      version := libVersion,
      scalaVersion := "2.10.2"
    )
  )
  
}