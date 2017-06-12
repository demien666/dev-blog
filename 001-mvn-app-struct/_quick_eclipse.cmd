set path=c:\java\apache-maven-3.1.1\bin
set MAVEN_OPTS=-Xmx512m -Xms256m -XX:MaxPermSize=128m
mvn eclipse:clean eclipse:eclipse -Dmaven.test.skip=true