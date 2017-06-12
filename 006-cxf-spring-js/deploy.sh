#!/bin/sh
mvn clean package
~/dev/tomcat/bin/shutdown.sh
rm -rf ~/dev/tomcat/webapps/spring-cxf-rest
rm ~/dev/tomcat/webapps/spring-cxf-rest.war
cp ./target/spring-cxf-rest.war ~/dev/tomcat/webapps
~/dev/tomcat/bin/startup.sh