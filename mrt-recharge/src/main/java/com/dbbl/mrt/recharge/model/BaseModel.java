package com.dbbl.mrt.recharge.model;

import com.dbbl.mrt.recharge.util.AppUtils;

public class BaseModel {

	public String toJson() {
		return "[" + this.getClass().getSimpleName() + "] => " + AppUtils.toJson(this);
	}

	public String toJsonNoNull() {
		return "[" + this.getClass().getSimpleName() + "] => " + AppUtils.toJsonNoNull(this);
	}
}
