package com.project.api.utils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class LocalDateTimeDeserializer extends StdDeserializer<LocalDateTime> {

	private static final long serialVersionUID = 4956786412652154920L;
	private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

	protected LocalDateTimeDeserializer() {
		super(LocalDateTime.class);
	}

	@Override
	public LocalDateTime deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		if (jp.getText() != null && !jp.getText().isEmpty()) {
			try {
				return LocalDateTime.parse(jp.getText(), DATETIME_FORMATTER);
				// return LocalDateTime.parse(jp.readValueAs(String.class));

			} catch (Exception e) {
				System.out.println(e);
				throw e;
			}
		} else {
			return null;
		}
	}

}
