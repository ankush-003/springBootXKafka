# Spring Boot X Kafka
- This is a simple example of how to use Kafka with Spring Boot.

## Architecture
![image](https://github.com/ankush-003/springBootXKafka/assets/94037471/d8a81c40-5388-44c8-8834-1b19d9d785da)

## Endpoints
![image](https://github.com/ankush-003/springBootXKafka/assets/94037471/0f29d6a2-a643-43d1-ac93-8b9053413e9f)

![image](https://github.com/ankush-003/springBootXKafka/assets/94037471/dbc05e96-46ea-41cf-af5a-5c4a7422a830)

## Running
Using `Docker`
```bash
docker run ankush003/springbootxkafka
```
Using `Maven`
```bash
mvn spring-boot:run
```

## Notes on Technology Used

### Jackson
- Jackson is a high-performance JSON processor for Java. It is a library for converting Java objects to and from JSON.

### Kafka
- `KafkaTemplate` is a high-level abstraction for sending messages to Kafka topics.
- `KafkaTemplate<Key, Value>` is a generic class, where 'Key' is the type of the key and 'Value' is the type of the value.

### Application Runners
- `ApplicationRunner` is a functional interface that can be used to run the code after the Spring Boot application has started.
- `CommandLineRunner` is a functional interface that can be used to run the code after the Spring Boot application has started.
- `@Order` annotation can be used to specify the order of execution of the Application Runners.

### ListenableFuture
- `ListenableFuture` is a generic interface that represents the result of an asynchronous computation.
- `ListenableFuture` is a part of the `org.springframework.util.concurrent` package.

## References
- [Spring for Apache Kafka Official Docs](https://docs.spring.io/spring-kafka/reference/kafka.html)
- [Server Sent Events with Spring](https://www.baeldung.com/spring-server-sent-events)

