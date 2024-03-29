FROM openjdk:17-alpine
MAINTAINER Elleined

COPY ./target/*.jar file-system-cryptography-api.jar

EXPOSE 8090
CMD ["java", "-jar", "file-system-cryptography-api.jar"]