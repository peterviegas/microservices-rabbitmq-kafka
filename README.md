# Project Scenario

## Objective:

One microservice (`OrderService`) receives orders via REST API and publishes messages to `RabbitMQ`.  
Another microservice (`NotificationService`) consumes messages from `RabbitMQ`, processes the data, and publishes notifications to a `Kafka` topic.  
A `Kafka` consumer (`ReportService`) listens to the messages published by the `NotificationService` and displays the notifications.

## Tags:

- `microservices`
- `OrderService`
- `NotificationService`
- `ReportService`
- `RabbitMQ`
- `Kafka`
- `RESTAPI`
- `MessageQueue`
- `EventDrivenArchitecture`
- `DistributedSystems`

## Step 1: Initial Setup

### Project Structure:

- **OrderService**: Microservice for orders using RabbitMQ
- **NotificationService**: Microservice to process messages from RabbitMQ and send them to Kafka
- **ReportService**: Microservice to consume notifications via Kafka

## Step 2: Create the `OrderService` Project
### Dependencies in `pom.xml`:

```xml
<dependencies>
    <!-- Spring Boot Starter Web -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <!-- Spring Boot Starter AMQP -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-amqp</artifactId>
    </dependency>
</dependencies>
````

### RabbitMQ Configuration in `application.yml`:

```yaml
server:
  port: 8081

spring:
  rabbitmq:
    host: localhost
    port: 5672
    username: guest
    password: guest
````

## Step 3: Create the NotificationService

### Dependencies in pom.xml:
````<dependencies>
    <!-- Spring Boot Starter AMQP -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-amqp</artifactId>
    </dependency>
    <!-- Spring Boot Starter Kafka -->
    <dependency>
        <groupId>org.springframework.kafka</groupId>
        <artifactId>spring-kafka</artifactId>
    </dependency>
</dependencies>
````

### RabbitMQ and Kafka Configuration (application.yml):
````
server:
  port: 8082

spring:
  rabbitmq:
    host: localhost
    port: 5672
    username: guest
    password: guest

spring:
  kafka:
    bootstrap-servers: localhost:9092
    producer:
      key-serializer: org.apache.kafka.common.serialization.StringSerializer
      value-serializer: org.apache.kafka.common.serialization.StringSerializer
````
## Passo 4: Create the ReportService
### Dependências no pom.xml:
````
<dependencies>
    <!-- Spring Boot Starter Kafka -->
    <dependency>
        <groupId>org.springframework.kafka</groupId>
        <artifactId>spring-kafka</artifactId>
    </dependency>
</dependencies>
````
### Kafka configuration (application.yml):
````
server:
  port: 8083

spring:
  kafka:
    bootstrap-servers: localhost:9092
    consumer:
      group-id: report-group
      auto-offset-reset: earliest
      key-deserializer: org.apache.kafka.common.serialization.StringDeserializer
      value-deserializer: org.apache.kafka.common.serialization.StringDeserializer
````

## Step 5: Start and Test the Microservices

1. Start the RabbitMQ and Kafka instances using Docker.
2. Start the three microservices:
- OrderService (port 8081)
- NotificationService (port 8082)
- ReportService (port 8083)
3. Test with Postman:
- Send a POST request to http://localhost:8081/api/orders with a JSON:

````
  {
  "orderId": 1,
  "item": "Laptop",
  "quantity": 2
}
````
4. Check:

- The message sent to RabbitMQ.
- The NotificationService consuming the message and publishing it to Kafka.
- The ReportService receiving and displaying the final message.


  
## Example creating with Spring Initializr
![image](https://github.com/user-attachments/assets/ea1338ef-0023-41e2-9dcb-53e93d065710)

Recommended Structure
````
src/main/java/com/pviegas/orderservice/
├── config/
│   └── RabbitConfig.java
├── controller/
│   └── OrderController.java
├── service/
│   └── OrderService.java
├── model/
│   └── Order.java
└── Application.java
````


### Aditional checklist
Check if RabbitMQ is running. If necessary, run RabbitMQ using Docker.
````docker run -d --hostname my-rabbit --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management````

### Test the Endpoint
Send a POST request
````
{
  "orderId": 123,
  "item": "Laptop",
  "quantity": 2
}
````
Access the RabbitMQ Dashboard
Open your browser and go to: http://localhost:15672.
Log in:
User: guest
Password: guest

![image](https://github.com/user-attachments/assets/d691aa0a-edea-43be-944c-b94c025b3e08)
