package org.demo.app.sub;

import org.demo.app.sub.config.ConfigMail;
import org.demo.app.sub.services.HelloService;
import org.demo.rabbitmq.amqp.fwk.RabbitBroker;
import org.demo.rabbitmq.amqp.fwk.RabbitLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rabbitmq.client.Connection;

// use @XxxScan when searched classes are not placed in the main application package or its sub-packages
// @ComponentScan({"org.demo.svc"}) 
// @EntityScan("org.demo.domain") // where to search entity classes (annotated with @Entity)
// @ConfigurationPropertiesScan("org.demo.conf")
@SpringBootApplication
public class AppSub implements CommandLineRunner {

	@Autowired 
	private ConfigMail confMail;

	@Autowired 
	private HelloService helloService;
	
	
    public static void main(String[] args) {
    	System.out.println("in main(args)");
        SpringApplication.run(AppSub.class, args);
    }
    
    @Override
    public void run(String... args) throws Exception {
    	System.out.println("in run(args)");
    	System.out.println("confMail in 'Application' : getHostName = " + confMail.getHostName());

    	helloService.hello("Bob");
    	
		RabbitLogger.setOutput(System.out);
		
//		ConnectionFactory factory = new ConnectionFactory();
//		System.out.println("ConnectionFactory created.");
////		factory.setHost("localhost");
////		factory.setPort(5672);
////		factory.setVirtualHost("/");
//		Connection connection = factory.newConnection("MyConnectionName");
//		System.out.println("Connection created.");
		
		RabbitBroker rabbitBroker = new RabbitBroker();
		Connection connection = rabbitBroker.newConnection("SUB connection");

		System.out.println("Starting consumer...");
		
		new ConsumerQueueC(connection).consume();
		
		System.out.println("Consumer started.");

    }
}