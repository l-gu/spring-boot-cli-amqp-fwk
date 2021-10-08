package org.demo.rabbitmq.amqp.fwk;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitBroker {

	private static final String BAD_URI = "invalid URI";
	private final ConnectionFactory factory ;

	public RabbitBroker() {
		super();
		this.factory = new ConnectionFactory();
//		factory.setHost("localhost");
//		factory.setPort(5672);
//		factory.setVirtualHost("/");

		// AMQP assigned port number is 5672 or 5671 for AMQPS (TLS/SSL encrypted AMQP).
		
		// CloudAMQP / Test connection OK
		// factory.setUri("amqps://tsevnjtg:OfWvwZHqwZBQCW8JcMbkAIGoc-b_ttmB@goose.rmq2.cloudamqp.com/tsevnjtg");
		
//		// CloudAMQP / Test connection OK 
//		factory.setUsername("tsevnjtg");
//		factory.setPassword("OfWvwZHqwZBQCW8JcMbkAIGoc-b_ttmB");
//		factory.setHost("goose.rmq2.cloudamqp.com");
//		//factory.setPort(5672);
//		factory.setVirtualHost("tsevnjtg");
	}
	
	public RabbitBroker(RabbitBrokerConfig conf) {
		super();
		this.factory = new ConnectionFactory();
		
		// Connection detailed configuration
		if ( conf.getHost() != null ) {
			factory.setHost(conf.getHost());
		}
		if ( conf.getPort() != null ) {
			factory.setPort(conf.getPort().intValue());
		}
		
		if ( conf.getUserName() != null ) {
			factory.setUsername(conf.getUserName());
		}
		if ( conf.getPassword() != null ) {
			factory.setPassword(conf.getPassword());
		}
		
		if ( conf.getVirtualHost() != null ) {
			factory.setVirtualHost(conf.getVirtualHost());
		}
		
		// Connection URI
		if ( conf.getUri() != null ) {
			try {
				factory.setUri(conf.getUri());
			} catch (KeyManagementException e) {
				throw new IllegalArgumentException(BAD_URI, e);
			} catch (NoSuchAlgorithmException e) {
				throw new IllegalArgumentException(BAD_URI, e);
			} catch (URISyntaxException e) {
				throw new IllegalArgumentException(BAD_URI, e);
			}
		}
		// TODO 
		// set factory config
		// etc
	}
	
	public Connection newConnection(String connectionName) throws IOException, TimeoutException {
		RabbitLogger.println("Creating connection...");
		Connection connection = factory.newConnection(connectionName);
		RabbitLogger.println("Connection created.");
		return connection;
	}
	
}
