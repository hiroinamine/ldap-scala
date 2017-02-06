name := """ldap-scala"""

version := "1.0"

scalaVersion := "2.11.7"

// Change this to another test framework if you prefer
libraryDependencies ++= Seq(

  // LDAP Nomration
  "com.github.jvican" % "scala-ldap-normation_2.11" % "1.0",

  // Apache LDAP
  "org.apache.directory.api" % "api-all" % "1.0.0-RC2",

  "org.scalatest" %% "scalatest" % "2.2.4" % "test"
)
