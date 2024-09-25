FROM openjdk:17-jdk-slim

EXPOSE 8080

ARG SPRING_KAFKA_SERVER
ENV SPRING_KAFKA_SERVER=${SPRING_KAFKA_SERVER}

WORKDIR /app

COPY target/kafka-producer-0.0.1-SNAPSHOT.jar /app/producer-0.0.1.jar

CMD ["java", "-jar", "producer-0.0.1.jar"]
