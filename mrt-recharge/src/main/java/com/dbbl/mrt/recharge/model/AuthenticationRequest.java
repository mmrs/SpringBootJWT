package com.dbbl.mrt.recharge.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationRequest {

	private String username;
	private String password;
	private String secret;
}
