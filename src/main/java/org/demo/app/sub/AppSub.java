package org.demo.app.sub;

import org.demo.app.sub.config.ConfigMail;
import org.demo.app.sub.config.RabbitBroker1Conf;
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

	//--- Configuration 
	@Autowired 
	private ConfigMail confMail;

	@Autowired 
	private RabbitBroker1Conf rabbitBroker1Conf;

	//--- Services 
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

    	System.out.println("Rabbit broker configuration :");
    	System.out.println(rabbitBroker1Conf);
    	
    	// Activate rabbit framework log
		// RabbitLogger.setOutput(System.out);
		
		// Declare broker with specific configuration
		RabbitBroker rabbitBroker1 = new RabbitBroker(rabbitBroker1Conf);

		System.out.println("Creating connection...");
		Connection connection = rabbitBroker1.newConnection("SUB connection");
		System.out.println("Connection created.");

		System.out.println("Starting consumer...");
		
		new ConsumerQueueC(connection).consume();
		
		System.out.println("Consumer started.");

    }
}