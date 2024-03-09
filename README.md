# Spring Boot X Kafka
- This is a simple example of how to use Kafka with Spring Boot.

# Notes

## Jackson
- Jackson is a high-performance JSON processor for Java. It is a library for converting Java objects to and from JSON.

## Kafka
- `KafkaTemplate` is a high-level abstraction for sending messages to Kafka topics.
- `KafkaTemplate<Key, Value>` is a generic class, where 'Key' is the type of the key and 'Value' is the type of the value.

## Application Runners
- `ApplicationRunner` is a functional interface that can be used to run the code after the Spring Boot application has started.
- `CommandLineRunner` is a functional interface that can be used to run the code after the Spring Boot application has started.
- `@Order` annotation can be used to specify the order of execution of the Application Runners.

