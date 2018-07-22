[![CircleCI](https://circleci.com/gh/gabriel-a/transfer-api-example.svg?style=svg)](https://circleci.com/gh/gabriel-a/transfer-api-example)

# transfer-api-example
Transfer API Example with Spring boot and in memory database

#To run
```
mvn spring-boot:run
```

#To run as docker
```
./mvnw install dockerfile:build
docker run -p 8080:8080 -t mydocker/transferapi:latest
```
