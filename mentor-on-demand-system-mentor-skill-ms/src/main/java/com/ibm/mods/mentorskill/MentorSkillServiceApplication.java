package com.ibm.mods.mentorskill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MentorSkillServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MentorSkillServiceApplication.class, args);
	}
	
}

