# Usage
1. Run rabbitmq docker container with exposed 5672 port.
2. Run EmitLog application from poll-app project to send messages to RabbitMQ.
3. Exit EmitLog application. OR Run AggregatorApplication on different port.
4. Run AggregatorApplication from aggregator-component project to receive messages from RabbitMQ.

