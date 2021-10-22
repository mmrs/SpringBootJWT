package com.dbbl.mrt.recharge.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TokenResponse extends BaseResponse {

	private String token;

}
