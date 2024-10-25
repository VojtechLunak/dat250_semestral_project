package hvl.aggregator;

import hvl.aggregator.config.Receiver;
import hvl.aggregator.model.PollData;
import hvl.aggregator.service.PollDataService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AggregatorApplication {

	static final String topicExchangeName = "poll-data-exchange";

	static final String queueName = "poll-data";

	@Autowired
	private PollDataService pollDataService;

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(AggregatorApplication.class, args).close();
	}

	@RabbitListener(queues = "poll-data")
	public void receiveMessage(String message) {
		System.out.println("Message read from poll-data : " + message);
		pollDataService.processPollData(message);
	}

	/*
	@Bean
	Queue queue() {
		return new Queue(queueName, true);
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange(topicExchangeName);
	}

	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("hvl.aggregator");
	}

	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
											 MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(queueName);
		container.setMessageListener(listenerAdapter);
		return container;
	}

	@Bean
	MessageListenerAdapter listenerAdapter(Receiver receiver) {
		return new MessageListenerAdapter(receiver, "receiveMessage");
	}

	 */

}