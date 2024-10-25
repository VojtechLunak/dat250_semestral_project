# Usage
1. Run rabbitmq docker container with exposed 5672 port.
2. Run EmitLog application from poll-app project to send messages to RabbitMQ.
3. Exit EmitLog application. OR Run AggregatorApplication on different port.
4. Run AggregatorApplication from aggregator-component project to receive messages from RabbitMQ.
5. After processing the received message, it should be saved to mongodb (running locally as of yet, will dockerize in the future).


# TODO
1. Send full message to RabbitMQ.
2. Process the message and save it to MongoDB.
3. Dockerize MongoDB.
4. Postprocessing? (how to use the saved data)

