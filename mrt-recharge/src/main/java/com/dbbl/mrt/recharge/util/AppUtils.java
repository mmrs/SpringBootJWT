package com.dbbl.mrt.recharge.util;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Component
public class AppUtils {

	private static Gson gsonMapper;

	static {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTimeAdapter());
		gsonMapper = gsonBuilder.create();
	}

	public static String toJsonString(Object object) {
		return gsonMapper.toJson(object);
	}
}
