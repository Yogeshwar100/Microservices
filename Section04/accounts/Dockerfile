#Start with a base image containing Java runtime
FROM openjdk:17-jdk-slim

#Maintainer name of the Docker image we are going to create.
MAINTAINER ytech.com

#Copy jar from target folder to docker image with same name
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

#To advise someone on executing commands when generating a container from a Docker image
ENTRYPOINT [ "java","-jar","accounts-0.0.1-SNAPSHOT.jar" ]