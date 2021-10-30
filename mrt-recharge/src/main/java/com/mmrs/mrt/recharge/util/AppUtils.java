package com.mmrs.mrt.recharge.util;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AppUtils {

	private static ObjectMapper noNullValueObjectMapper;
	private static ObjectMapper objectMapper;

	static {

//		DateFormat dateFormat = new SimpleDateFormat();

		objectMapper = new ObjectMapper();
		objectMapper.findAndRegisterModules();
		objectMapper.setVisibility(PropertyAccessor.ALL, Visibility.NONE);
		objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
//		objectMapper.setDateFormat(dateFormat);

		noNullValueObjectMapper = new ObjectMapper();
		noNullValueObjectMapper.findAndRegisterModules();
		noNullValueObjectMapper.setVisibility(PropertyAccessor.ALL, Visibility.NONE);
		noNullValueObjectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
//		noNullValueObjectMapper.setDateFormat(dateFormat);
		noNullValueObjectMapper.setSerializationInclusion(Include.NON_EMPTY);
	}

	public static String toJson(Object object) {
		try {
			return objectMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			log.error("Error occured while converting JSON. Message: " + e.getLocalizedMessage());
			return null;
		}
	}

	public static String toJsonNoNull(Object object) {
		try {
			return noNullValueObjectMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			log.error("Error occured while converting JSON. Message: " + e.getLocalizedMessage());
			return null;
		}
	}
}
