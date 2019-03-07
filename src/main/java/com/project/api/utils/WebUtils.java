package com.project.api.utils;

import javax.servlet.http.HttpServletRequest;

import com.github.slugify.Slugify;

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

	public static String generateSlug(String name, long id) {
		
		String slug = null;
		
		if (name != null) {
			StringBuilder strBuilder = new StringBuilder(name);
			strBuilder.append("-");
			strBuilder.append(String.valueOf(id));

			Slugify slg = new Slugify();
			slug = slg.slugify(strBuilder.toString());
		}

		return slug;
	}

}