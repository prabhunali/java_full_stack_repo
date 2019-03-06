package com.ibm.mods.payment.security.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class URIRootBuilder {
	
	@Value("${server.protocol}")
	private String protocol;
	
	@Value("${server.hostname}")
	private String hostname;
	
	@Value("${zuul.server.port}")
	private String zuulPort;
	
	@Value("${user.zuul.route}")
	private String userPath;
	
	@Value("${admin.zuul.route}")
	private String adminPath;
	
	@Value("${mentorskill.zuul.route}")
	private String mentorSkillPath;
	
	@Value("${training.zuul.route}")
	private String trainingPath;
	
	@Value("${payment.zuul.route}")
	private String paymentPath;
	
	public String getRootURL() {
		return protocol + "://" + hostname + ":";
	}
	
	public String zuulRootURL() {
		return getRootURL() + zuulPort + "/";
	}
	
	public String userRootURL() {
		return zuulRootURL() + userPath;
	}
	
	public String adminRootURL() {
		return zuulRootURL() + adminPath;
	}
	
	public String mentorSkillRootURL() {
		return zuulRootURL() + mentorSkillPath;
	}

	public String trainingRootURL() {
		return zuulRootURL() + trainingPath;
	}
	
	public String paymentRootURL() {
		return zuulRootURL() + paymentPath;
	}

}
