package hvl.aggregator.listener;

import hvl.aggregator.service.PollDataService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PollMessageListener {

    private final PollDataService pollDataService;

    public PollMessageListener(PollDataService pollDataService) {
        this.pollDataService = pollDataService;
    }

    @RabbitListener(queues = "poll-data")
    public void receiveMessage(String message) {
        System.out.println("Message read from poll-data : " + message);
        //pollService.processPollData(message);
    }
}