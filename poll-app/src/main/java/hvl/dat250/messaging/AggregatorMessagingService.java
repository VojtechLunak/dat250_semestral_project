package hvl.dat250.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;


@Service
public class AggregatorMessagingService {

    private final RabbitTemplate template;

    public AggregatorMessagingService(RabbitTemplate template) {
        this.template = template;
    }

    public void sendMessage(String message) {
        String logMessage = "Poll::" + message + "::" + System.currentTimeMillis();
        System.out.println("Sending message: " + logMessage);
        template.convertAndSend(RabbitMQConfig.QUEUE_NAME, logMessage);
    }
}
