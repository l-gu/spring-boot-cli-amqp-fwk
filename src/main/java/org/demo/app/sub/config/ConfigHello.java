package org.demo.app.sub.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

// The official documentation advises that we isolate configuration properties into separate POJOs.
// @Configuration : Spring creates a Spring bean in the application context.
// works best with hierarchical properties that all have the same prefix (here "mail" prefix)
// The classpath scanner enabled by @SpringBootApplication finds the ConfigProperties class, 
// even though we didn't annotate this class with @Component.
@Configuration
// @PropertySource("classpath:foo.properties") // foo.properties] cannot be opened because it does not exist
// Specific configuration file
@PropertySource("classpath:application.properties") // OK but useless (default value)
@ConfigurationProperties(prefix = "hello")
public class ConfigHello {
	
    private String before;
    private String after;
    
	public String getBefore() {
		return before;
	}
	public void setBefore(String before) {
		this.before = before;
	}
	
	public String getAfter() {
		return after;
	}
	public void setAfter(String after) {
		this.after = after;
	}
    
}
