package org.demo.app.sub.config;

import org.demo.rabbitmq.amqp.fwk.RabbitBrokerConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
// @PropertySource("classpath:foo.properties") // specific file (foo.properties)
// @PropertySource("classpath:application.properties") // useless (default value)
@ConfigurationProperties(prefix = "rabbit1")
public class RabbitBroker1Conf extends RabbitBrokerConfig {


}
