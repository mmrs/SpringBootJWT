package com.dbbl.mrt.recharge.model;

import java.time.LocalDateTime;

import com.dbbl.mrt.recharge.util.AppUtils;
import com.dbbl.mrt.recharge.util.GsonLocalDateTimeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class BaseModel {

	private static Gson gsonNullMapper;

	static {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTimeAdapter());
		gsonNullMapper = gsonBuilder.serializeNulls().create();
	}

	@Override
	public String toString() {
		return "[" + this.getClass().getSimpleName() + "] => " + gsonNullMapper.toJson(this);
	}

	public String toJsonNoNull() {
		return "[" + this.getClass().getSimpleName() + "] => " + AppUtils.toJsonString(this);
	}
}
