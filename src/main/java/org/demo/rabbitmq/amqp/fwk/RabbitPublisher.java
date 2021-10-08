package org.demo.rabbitmq.amqp.fwk;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.AMQP.BasicProperties;

public class RabbitPublisher {

	private final Channel channel; 
	private final String exchange;

	public RabbitPublisher(Connection connection, String exchange) throws IOException {
		super();
		// Check mandatory arguments
		if ( connection == null ) {
			throw new IllegalArgumentException("Connection is null");
		}
		if ( exchange == null ) {
			throw new IllegalArgumentException("Exchange is null");
		}
		this.channel = connection.createChannel();
		this.exchange = exchange;
	}

	public Channel getChannel() {
		return channel;
	}

	public String getExchange() {
		return exchange;
	}

	public void publish(String messageBody, String routingKey) throws IOException {
		publish(messageBody, null, routingKey);
	}
	
	public void publish(String messageBody, BasicProperties messageProperties, String routingKey) throws IOException {
		// Check mandatory arguments
		if ( messageBody == null ) {
			throw new IllegalArgumentException("Message body is null");
		}
		if ( routingKey == null ) {
			throw new IllegalArgumentException("Routing key is null");
		}

		// Check channel state
		if ( channel.isOpen() ) {
			channel.basicPublish(exchange, routingKey, messageProperties, messageBody.getBytes());		
		}
		else {
			throw new IllegalStateException("Channel is closed. Cannot publish message '" + messageBody + "'");
		}
	}

}
