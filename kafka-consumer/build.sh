#!/bin/bash
IMAGE_NAME="simple-consumer:latest"
DOCKERFILE=DockerfileMaven

docker build -t ${IMAGE_NAME} -f ${DOCKERFILE} .
