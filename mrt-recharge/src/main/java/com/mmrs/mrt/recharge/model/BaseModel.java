package com.mmrs.mrt.recharge.model;

import com.mmrs.mrt.recharge.util.AppUtils;

public class BaseModel {

	public String toJson() {
		return "[" + this.getClass().getSimpleName() + "] => " + AppUtils.toJson(this);
	}

	public String toJsonNoNull() {
		return "[" + this.getClass().getSimpleName() + "] => " + AppUtils.toJsonNoNull(this);
	}
}
