package com.dbbl.mrt.recharge.model;

import lombok.Data;

@Data
public class BaseResponse {

	private Integer status;
	private String rspCode;
	private String rspMsg;

	public BaseResponse() {
		status = 0;
		rspCode = "SUC-000";
		rspMsg = "Request Successfull";
	}
}
