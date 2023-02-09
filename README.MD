# Spring Kafka

This is an example how to use Spring Kafka.

## Producer
The application uses Spring Kafka to produce messages to the topic.
The topic name is specified in the application properties under `app.kafka.messageTopicSettings.name`.
The number of replicas and partitions can also be adjusted with the following properties:
- `app.kafka.messageTopicSettings.replicas`, with a minimum value of 1 and a maximum value of 100.
- `app.kafka.messageTopicSettings.partitions`, with a minimum value of 1 and a maximum value of 100.

To send messages to the topic, use this curl:
```commandline
curl --location --request POST 'http://localhost:8080/sender/send' \
--header 'Content-Type: application/json' \
--data-raw '{
"id": 1,
"text": "Hello World!"
}'
```

## Consumer
The application uses Spring Kafka to consume messages from the topic specified in the properties under `app.kafka.messageTopicSettings.name`.
The following properties can be configured:
- `spring.kafka.consumer.group-id` - specifies the consumer group the application belongs to.
- `spring.kafka.consumer.auto-offset-reset` - specifies the behavior when there is no initial offset in Kafka or if an offset is out of range.
