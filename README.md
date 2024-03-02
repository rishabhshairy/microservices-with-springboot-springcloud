# microservices-with-springboot-springcloud
Learning Udemy Course : Master Microservices with Spring Boot and Spring Cloud

### Repository Contents
1.restful-web-services --> contains the code for basic understanding of how RESTful webservices work

### Standard Ports for All Application
Limits Microservice             `8080,8081...`

Spring Cloud Config Server      `8888`

Currency-Exchange               `8000,8001...`

Currency-Conversion             `8100,8101...`

Naming Server                   `8761`

Gateway                         `8765`

Zipkin                          `9411`

## Building docker images:

`spring-boot:build-image -DskipTests`

## Docker Images:

`docker.io/rishabhshairy/nmsv2-naming-server:0.0.1-SNAPSHOT`

`docker.io/rishabhshairy/nmsv2-apigateway-service:0.0.1-SNAPSHOT`

`docker.io/rishabhshairy/nmsv2-currency-conversion-service:0.0.1-SNAPSHOT`

`docker.io/rishabhshairy/nmsv2-currency-exchange-service:0.0.1-SNAPSHOT`
