FROM java:8-jdk-alpine

RUN apk update && apk add bash

RUN mkdir -p /opt/app
ENV PROJECT_HOME /opt/app

COPY build/libs/prova-0.0.1-SNAPSHOT.jar $PROJECT_HOME/prova-0.0.1-SNAPSHOT.jar

WORKDIR $PROJECT_HOME

CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=prod", "-jar", "./prova-0.0.1-SNAPSHOT.jar"]
