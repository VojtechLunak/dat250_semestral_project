package hvl.aggregator.listener;

import hvl.aggregator.service.PollService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PollMessageListener {

    private final PollService pollService;

    public PollMessageListener(PollService pollService) {
        this.pollService = pollService;
    }

    @RabbitListener(queues = "poll-data")
    public void receiveMessage(String message) {
        System.out.println("Message read from poll-data : " + message);
        //pollService.processPollData(message);
    }
}