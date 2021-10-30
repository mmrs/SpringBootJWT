package com.mmrs.mrt.recharge.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = false)
@EqualsAndHashCode(callSuper = false)
@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
public class BaseResponse extends BaseModel{

	private Integer status;
	private String rspCode;
	private String rspMsg;

	public BaseResponse() {
		status = 0;
		rspCode = "SUC-000";
		rspMsg = "Request Successfull";
	}
}
