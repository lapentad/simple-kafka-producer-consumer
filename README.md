# simple-kafka-producer-consumer
Two simple microservices acting as kafka producer and consumer, using Spring Kafka Template and Listener, and Reactor to avail of Asyncronus calls.

To build and create the docker images, run from ./kafka-producer and from ./kafka-consumer
```
mvn clean install
```

To run a small integration test, and start a consumer and producer producing 10 messages
```
bash start.sh
```
