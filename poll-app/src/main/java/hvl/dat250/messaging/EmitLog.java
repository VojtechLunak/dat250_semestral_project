package hvl.dat250.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class EmitLog {

//    public static void main(String[] argv) throws Exception {
//        SpringApplication.run(EmitLog.class, argv);
//    }
//
//    @Bean
//    public ApplicationRunner runner(RabbitTemplate template) {
//        return args -> {
//            String message = "Poll::" + System.currentTimeMillis();
//            System.out.println("Sending message: " + message);
//            template.convertAndSend(RabbitMQConfig.QUEUE_NAME,  message);
//        };
//    }
}
