#!/bin/bash
IMAGE_NAME="simple-producer:latest"
DOCKERFILE=DockerfileMaven

docker build -t ${IMAGE_NAME} -f ${DOCKERFILE} .
