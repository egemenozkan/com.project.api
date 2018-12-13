package com.project.api.utils;

import javax.servlet.http.HttpServletRequest;

public class WebUtils {

	public static String getClientIp(HttpServletRequest request) {

		String remoteAddr = "";
		try {
			if (request != null) {
				remoteAddr = request.getHeader("X-FORWARDED-FOR");
				if (remoteAddr == null || "".equals(remoteAddr)) {
					remoteAddr = request.getRemoteAddr();
				}
			}
		} catch (Exception e) {
			//
		}

		return remoteAddr;
	}

}