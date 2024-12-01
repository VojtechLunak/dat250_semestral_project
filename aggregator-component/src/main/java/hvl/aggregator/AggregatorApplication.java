package hvl.aggregator;

import hvl.aggregator.service.PollDataService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AggregatorApplication {
	@Autowired
	private PollDataService pollDataService;

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(AggregatorApplication.class, args);
	}

	@RabbitListener(queues = "poll-data")
	public void receiveMessage(String message) {
		System.out.println("Message read from poll-data : " + message);
		pollDataService.processPollData(message);
	}
}