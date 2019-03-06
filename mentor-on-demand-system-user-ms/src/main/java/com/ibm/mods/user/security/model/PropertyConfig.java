package com.ibm.mods.user.security.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.yml")
public class PropertyConfig {
	
	@Value("${server.port}")
	private String port;
	
	@Value("${eureka.instance.hostname}")
	private String hostname;
	
	private static final String protocol = "http";
	
	public String getRootURL() {
		return protocol + "://" + hostname + ":" + port;
	}

}
