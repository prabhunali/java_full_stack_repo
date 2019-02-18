package com.ibm.mods.auth.util;

import java.net.MalformedURLException;
import java.util.UUID;
import java.net.URL;
import javax.servlet.http.HttpServletRequest;

public class Utils {

	public static String generateRegistrationCode() {
		UUID responseId = UUID.randomUUID();
		return responseId.toString();
	}
	
	public static String getRootURI(HttpServletRequest request) throws MalformedURLException {
		URL requestURL = new URL(request.getRequestURL().toString());
	    String port = requestURL.getPort() == -1 ? "" : ":" + requestURL.getPort();
	    return requestURL.getProtocol() + "://" + requestURL.getHost() + port;
	}
	
}
