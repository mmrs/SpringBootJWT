package com.mmrs.mrt.recharge.model;

import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = false)
@EqualsAndHashCode(callSuper = false)
@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
public class FunctionResult extends BaseModel {

	private boolean isValid;
	private String msgCode;
	private String msgBody;
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

	@JsonIgnore
	public String getMessageString() {
		if (StringUtils.isBlank(msgCode)) {
			return msgBody;
		} else {
			return msgCode + ": " + msgBody;
		}
	}

	@JsonIgnore
	public String getSummary() {
		return "Summary => isValid: " + isValid + ". Info: " + getMessageString();
	}

	public Object getData(String key) {
		return this.data.get(key);
	}

	@JsonIgnore
	public FunctionResult setData(String key, Object object) {
		this.data.put(key, object);
		return this;
	}
}
