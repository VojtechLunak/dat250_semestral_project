package hvl.dat250.messaging;

import org.springframework.amqp.core.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String QUEUE_NAME = "poll-data";

    @Bean
    public Queue myQueue() {
        return new Queue(QUEUE_NAME, false);
    }
}