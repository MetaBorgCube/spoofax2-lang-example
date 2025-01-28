#!/bin/sh

cp ../../front/front/target/metaborg/stratego.jar front.jar
mvn org.apache.maven.plugins:maven-install-plugin:2.3.1:install-file -Dfile=front.jar -DgroupId=front.trans -DartifactId=front -Dversion=0.1.0 -Dpackaging=jar -DlocalRepositoryPath="$(pwd)/repo/"

