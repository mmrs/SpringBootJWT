package com.dbbl.mrt.recharge.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
public class TokenResponse extends BaseResponse {

	private String token;

}
