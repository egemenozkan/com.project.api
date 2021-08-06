package com.vantalii.api.utils;

import com.project.slugify.Slugify;

public class ApiUtils {

	public static String generateSlug(String name, long id) {

		String slug = null;

		if (name != null && !name.isBlank()) {
			StringBuilder strBuilder = new StringBuilder(name);
			strBuilder.append("-");
			strBuilder.append(String.valueOf(id));

			Slugify slg = new Slugify();
			slug = slg.slugify(strBuilder.toString());
		}

		return slug;
	}

}