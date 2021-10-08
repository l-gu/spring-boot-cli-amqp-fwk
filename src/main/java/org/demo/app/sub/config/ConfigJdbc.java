package org.demo.app.sub.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

// The official documentation advises that we isolate configuration properties into separate POJOs.
// @Configuration : Spring creates a Spring bean in the application context.
// works best with hierarchical properties that all have the same prefix (here "mail" prefix)
// The classpath scanner enabled by @SpringBootApplication finds the ConfigProperties class, 
// even though we didn't annotate this class with @Component.
@Configuration
public class ConfigJdbc {
	
	@Value( "${jdbc.url:aDefaultUrl}" )
	private String jdbcUrl;

	public String getJdbcUrl() {
		return jdbcUrl;
	}
	
	
}
