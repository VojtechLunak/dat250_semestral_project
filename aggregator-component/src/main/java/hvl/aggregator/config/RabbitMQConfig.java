package hvl.aggregator.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_NAME = "poll-data";

    @Bean
    public Queue pollDataQueue() {
        return new Queue(QUEUE_NAME, true);
    }
}