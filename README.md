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

