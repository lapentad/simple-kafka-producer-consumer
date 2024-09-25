#!/bin/bash
source ./utils/utils.sh

docker compose up -d

# Wait for the Kafka container to be running
wait_for_container "broker" "Kafka Server started"
wait_for_container "kafka-producer" "Started KafkaProducerApplication"
wait_for_container "kafka-consumer" "Started KafkaConsumerApplication"

# Once Kafka container is running, start the producers and consumers
echo "Kafka container is up and running. Starting producer and consumer..."
space
echo "Start a CONSUMER"
curl -X GET "http://localhost:9090/start"
space
sleep 3
for i in {1..10}; do
  echo
  curl -X GET "http://localhost:8080/publish/$i"
  sleep 1
done
sleep 3
space
echo "Stop a CONSUMER"
curl -X GET "http://localhost:9090/stop"

space
echo "Demo Producer Docker logs "
docker logs kafka-producer | grep c.d.k.controller.KafkaController
space
echo "Demo Consumer Docker logs "
docker logs kafka-consumer | grep c.d.kafkaconsumer.service.KafkaConsumer
space

echo "Done."

containers=("kafka-producer" "kafka-consumer")

for container in "${containers[@]}"; do
  if docker logs "$container" | grep -q ERROR; then
    echo "Errors found in logs of $container"
    docker logs "$container" | grep ERROR
    echo "FAIL"
    exit 1
  else
    echo "No errors found in logs of $container"
  fi
done
echo "SUCCESS"
docker compose down
exit 0
