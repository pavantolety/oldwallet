package com.oldwallet.util;

import org.apache.commons.lang.RandomStringUtils;

public class AuthenticationUtils {

	public static String generateTokenForAuthentication() {
		String token = RandomStringUtils.random(10, true, true);
		return System.currentTimeMillis() + token;
	}

}
