package com.ibm.mods.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class MentorOnDemandSystemDiscoveryServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MentorOnDemandSystemDiscoveryServerApplication.class, args);
	}

}

