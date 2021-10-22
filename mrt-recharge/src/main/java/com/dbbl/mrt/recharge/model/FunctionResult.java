package com.dbbl.mrt.recharge.model;

import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;
import lombok.Setter;

public class FunctionResult extends BaseModel {

	@Setter
	@Getter
	private boolean isValid;
	@Getter
	@Setter
	private String msgCode;
	@Getter
	@Setter
	private String msgBody;
	@Getter
	@Setter
	public HashMap<String, Object> data;

	public FunctionResult() {
		super();
		this.isValid = true;
		this.data = new HashMap<String, Object>();
	}

	public FunctionResult(boolean isValid, String msgCode, String msgBody) {
		this();
		this.isValid = isValid;
		this.msgCode = msgCode;
		this.msgBody = msgBody;
	}

	public String getMessageString() {
		if (StringUtils.isBlank(msgCode)) {
			return msgBody;
		} else {
			return msgCode + ": " + msgBody;
		}
	}

	public String getSummary() {
		return "Summary => isValid: " + isValid + ". Info: " + getMessageString();
	}

	public Object getData(String key) {
		return this.data.get(key);
	}

	public FunctionResult setData(String key, Object object) {
		this.data.put(key, object);
		return this;
	}
}
