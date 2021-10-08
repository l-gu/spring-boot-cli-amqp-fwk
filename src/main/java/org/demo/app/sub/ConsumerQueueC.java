package org.demo.app.sub;

import java.io.IOException;

import org.demo.rabbitmq.amqp.fwk.RabbitConsumer;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Connection;

public class ConsumerQueueC extends RabbitConsumer {

	private static final String QUEUE_NAME = "queue-c" ;
	
	public ConsumerQueueC(Connection connection) throws IOException {
		super(connection, QUEUE_NAME);
	}
	
	@Override
	protected Ack processMessage(String messageBody, BasicProperties messageProperties) {
		System.out.println("Subscriber : processing message : " + messageBody);
		if ( getRedeliveryCount(messageProperties) > 2 ) {
			//rejectMessage();
			return Ack.REJECT_MESSAGE;
		}
		sleep(1000L);
		if ( messageBody.contains("OOPS") ) {
			//rejectMessage();
			return Ack.REJECT_MESSAGE;
		}
		else if ( messageBody.contains("ARGH") ) {
			//requeueMessage();
			return Ack.REQUEUE_MESSAGE;

			// this message will become a "poison message"
			// message headers : 'x-delivery-count' : 2, 3 ,4, etc
			// Quorum queues keep track of the number of unsuccessful delivery attempts 
			// and expose it in the "x-delivery-count" header that is included with any redelivered message.
		}
		else {
			// Normal processing => ACK MESSAGE
			return Ack.ACK_MESSAGE;
		}
	}
	
	private void sleep(long n) {
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			throw new RuntimeException("Sleep interrupted");
		}
	}	
}
