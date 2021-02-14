#!/bin/bash

# Compiling mailing service
mvn -f mailing-service/pom.xml clean package

# Compiling subcription service
mvn -f subcription-service/pom.xml clean package

# Compiling BFF
mvn -f bff-gateway/pom.xml clean package

# Building docker images
docker-compose -f docker-compose.yml build

# Start docker environment
docker-compose -f docker-compose.yml up -d

echo "Success!"
