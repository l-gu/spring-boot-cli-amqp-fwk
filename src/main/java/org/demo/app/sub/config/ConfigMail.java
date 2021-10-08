package org.demo.app.sub.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

// The official documentation advises that we isolate configuration properties into separate POJOs.
// @Configuration : Spring creates a Spring bean in the application context.
// works best with hierarchical properties that all have the same prefix (here "mail" prefix)
// The classpath scanner enabled by @SpringBootApplication finds the ConfigProperties class, 
// even though we didn't annotate this class with @Component.
@Configuration
@ConfigurationProperties(prefix = "mail")
public class ConfigMail {
	
    private String hostName;
    private int port;
    private String from;
    
	public String getHostName() {
		return hostName;
	}
	// if no setter : Failed to bind properties under 'mail'
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}

}
