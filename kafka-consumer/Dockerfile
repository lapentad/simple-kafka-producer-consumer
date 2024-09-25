FROM openjdk:17-jdk-slim

EXPOSE 9090

ARG SPRING_KAFKA_SERVER
ENV SPRING_KAFKA_SERVER=${SPRING_KAFKA_SERVER}

WORKDIR /app

COPY target/kafka-consumer-0.0.1-SNAPSHOT.jar /app/consumer-0.0.1.jar

CMD ["java", "-jar", "consumer-0.0.1.jar"]
