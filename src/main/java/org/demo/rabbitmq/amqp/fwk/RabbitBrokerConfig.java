package org.demo.rabbitmq.amqp.fwk;

public class RabbitBrokerConfig {

	private static final String EOL = "\n";
	
	private String  host ;
	private Integer port ;
	private String  virtualHost ;
	private String  userName ;
	private String  password ;

	private String uri ;
	
	public RabbitBrokerConfig() {
		super();
	}

	private String trim(String s) {
		return s != null ? s.trim() : null ;
	}
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = trim(host);
	}

	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}

	public String getVirtualHost() {
		return virtualHost;
	}
	public void setVirtualHost(String virtualHost) {
		this.virtualHost = trim(virtualHost);
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = trim(userName);
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = trim(password);
	}

	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = trim(uri);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getClass().getSimpleName()).append(" : ").append(EOL);

		sb.append(" . host = '").append(host).append("'").append(EOL);
		sb.append(" . port = ").append(port).append(EOL);
		sb.append(" . virtualHost = '").append(virtualHost).append("'").append(EOL);
		sb.append(" . userName = '").append(userName).append("'").append(EOL);
		sb.append(" . password = '").append(password).append("'").append(EOL);

		sb.append(" . uri = '").append(uri).append("'").append(EOL);
		
		return sb.toString();
	}
	
}
