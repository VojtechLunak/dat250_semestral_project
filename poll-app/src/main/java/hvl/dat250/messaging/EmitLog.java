package hvl.dat250.messaging;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EmitLog {

    private static final String EXCHANGE_NAME = "poll-data-exchange";
    private static final String QUEUE_NAME = "poll-data";
    private static final String ROUTING_KEY = "poll-data-1";

    public static void main(String[] argv) throws Exception {
        SpringApplication.run(EmitLog.class, argv);
    }

    @Bean
    public ApplicationRunner runner(RabbitTemplate template) {
        return args -> {
            ApplicationContext context = new AnnotationConfigApplicationContext(RabbitMQConfig.class);
            AmqpTemplate amqpTemplate = context.getBean(AmqpTemplate.class);
            System.out.println("Sending message...");
            amqpTemplate.convertAndSend( "Poll::" + System.currentTimeMillis());
        };
    }
}
