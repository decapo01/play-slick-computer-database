name := "computerDatabaseSample"

//PlayKeys.playOmnidoc := false

scalaVersion := "2.12.3"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

libraryDependencies ++= Seq(
	guice,
	"com.typesafe.play" %% "play-slick" % "3.0.1",
  "com.typesafe.play" %% "play-slick-evolutions" % "3.0.1",
	"org.mindrot" % "jbcrypt" % "0.4",
	"org.apache.commons" % "commons-text" % "1.4",
	"org.apache.commons" % "commons-lang3" % "3.7",
	"org.passay" % "passay" % "1.3.1",
	"com.h2database"   	%  "h2"                       % "1.4.196",
	"org.scalatestplus.play" %% "scalatestplus-play" % "3.0.0" % "test"
)

// META-INF discarding
assemblyMergeStrategy in assembly := {
  case manifest if manifest.contains("MANIFEST.MF") =>
    // We don't need manifest files since sbt-assembly will create
    // one with the given settings
    MergeStrategy.discard
  case referenceOverrides if referenceOverrides.contains("reference-overrides.conf") =>
    // Keep the content for all reference-overrides.conf files
    MergeStrategy.concat
  case x =>
    // For all the other files, use the default sbt-assembly merge strategy
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}
