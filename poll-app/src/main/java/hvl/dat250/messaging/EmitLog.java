package hvl.dat250.messaging;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EmitLog {

    private static final String EXCHANGE_NAME = "poll-data-exchange";
    private static final String ROUTING_KEY = "poll-data";

    public static void main(String[] argv) throws Exception {
        SpringApplication.run(EmitLog.class, argv);
    }

    @Bean
    public ApplicationRunner runner(RabbitTemplate template) {
        return args -> {
            template.convertAndSend(ROUTING_KEY, "Hello, world!");
        };
    }

    @Bean
    public Queue myQueue() {
        return new Queue(ROUTING_KEY, false);
    }
}
