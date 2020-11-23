FROM openjdk:14-jdk-alpine

EXPOSE 8081

ADD target/mediator-0.0.1-SNAPSHOT.jar mediator-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "mediator-0.0.1-SNAPSHOT.jar"]