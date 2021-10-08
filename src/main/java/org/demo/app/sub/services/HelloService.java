package org.demo.app.sub.services;

import org.demo.app.sub.config.ConfigHello;
import org.demo.app.sub.config.ConfigJdbc;
import org.demo.app.sub.config.ConfigMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

	@Autowired 
	private ConfigMail confMail;
	
	@Autowired 
	private ConfigJdbc confJdbc;
	
	@Autowired 
	private ConfigHello confHello;
	
	public void hello(String name) {
		
    	System.out.println("confMail : getHostName = " + confMail.getHostName());

    	System.out.println("confJdbc : getJdbcUrl = " + confJdbc.getJdbcUrl());

    	System.out.println("confHello : getBefore = " + confHello.getBefore());
    	System.out.println("confHello : getAfter  = " + confHello.getAfter());

		System.out.println("Hello " + name);
	}
}