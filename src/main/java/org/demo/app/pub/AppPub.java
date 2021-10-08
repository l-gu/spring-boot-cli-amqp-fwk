package org.demo.app.pub;

import org.demo.rabbitmq.amqp.fwk.RabbitBroker;
import org.demo.rabbitmq.amqp.fwk.RabbitLogger;
import org.demo.rabbitmq.amqp.fwk.RabbitPublisher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rabbitmq.client.Connection;

@SpringBootApplication
public class AppPub implements CommandLineRunner {

    public static void main(String[] args) {
    	System.out.println("in main(args)");
        SpringApplication.run(AppPub.class, args);
    }
    
    @Override
    public void run(String... args) throws Exception {
    	System.out.println("in run(args)");

    	RabbitLogger.setOutput(System.out);
    	/***
		ConnectionFactory factory = new ConnectionFactory();
		System.out.println("ConnectionFactory created.");
		Connection connection = factory.newConnection("MyConnectionName");
		System.out.println("Connection created.");
		**/

		RabbitBroker rabbitBroker = new RabbitBroker();
		Connection connection = rabbitBroker.newConnection("PUB connection");

		System.out.println("Publishing messages...");
		
		RabbitPublisher publisher = new RabbitPublisher(connection, "ex-direct-c");
		String routingKey = "routing-key-c";
		
		for ( int i = 1 ; i <= 12 ; i++ ) {
			String msg = "My test message #" + i ;
			int modulo = i % 3 ;
			if ( modulo == 2 ) {
				msg = msg + " OOPS!";
			} 
			else if ( modulo == 0 ) {
				msg = msg + " ARGH!";  
			}
			System.out.println("Publishing message : " + msg);
			//publisher.publish(msg, null, routingKey);
			publisher.publish(msg, routingKey);
		}

		// publisher.publish(null, null, routingKey); // IllegalArgumentException: Message body is null
		// publisher.publish("azert", null, null); // IllegalArgumentException: Routing key is null
		
		System.out.println("Closing publisher's channel.");
		publisher.getChannel().close();
		
		System.out.println("Closing connection (and all its channels).");
		connection.close();
		
		System.out.println("End.");

    }
}