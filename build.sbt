import sbt.util

name := """formicacid"""
organization := "de.themonstrouscavalca"
maintainer := "oss@themonstrouscavalca.de"
version := "2023.05.1-SNAPSHOT"

scalaVersion := "2.13.12"

resolvers ++= Seq(Resolver.mavenLocal,
    "Sonatype snapshots repository" at "https://oss.sonatype.org/content/repositories/snapshots/",
    "Shibboleth releases" at "https://build.shibboleth.net/nexus/content/repositories/releases/",
    "Aspose Releases" at "https://artifact.aspose.com/repo/",
    "Atlassian Releases" at "https://maven.atlassian.com/public/",
    "Typesafe Repository" at "https://repo.typesafe.com/typesafe/releases/"
)

lazy val root = (project in file(".")).enablePlugins(SbtTwirl)

val jacksonVersion = "2.14.2"

libraryDependencies ++= Seq(
    "de.themonstrouscavalca"        %% "dbaser"                 % "2022.6.3-SNAPSHOT",
    "org.apache.commons"            % "commons-lang3"           % "3.5",
    "commons-validator"             % "commons-validator"       % "1.7",
    "javax.mail"                    % "mail"                    % "1.5.0-b01",
    "jakarta.xml.bind"              % "jakarta.xml.bind-api"    % "2.3.3",
    "com.fasterxml.jackson.core"    % "jackson-databind"        % jacksonVersion,
    "com.fasterxml.jackson.core"    % "jackson-annotations"     % jacksonVersion,
    "com.fasterxml.jackson.core"    % "jackson-core"            % jacksonVersion,
    "commons-codec"                 % "commons-codec"           % "1.10",
    "com.opencsv"                   % "opencsv"                 % "3.8",
    "junit"                         % "junit"                   % "4.13.1"                  % Test,
    "com.novocode"                  % "junit-interface"         % "0.11"                    % Test,
    "org.slf4j"                     % "slf4j-api"               % "2.0.5",
    "org.slf4j"                     % "slf4j-simple"            % "2.0.5"
)

Test / test / logLevel := util.Level.Error

logBuffered := false
scalacOptions += "-deprecation"
