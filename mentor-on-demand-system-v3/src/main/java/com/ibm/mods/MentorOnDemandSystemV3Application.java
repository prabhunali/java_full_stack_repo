package com.ibm.mods;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MentorOnDemandSystemV3Application {

	public static void main(String[] args) {
		SpringApplication.run(MentorOnDemandSystemV3Application.class, args);
	}

}

