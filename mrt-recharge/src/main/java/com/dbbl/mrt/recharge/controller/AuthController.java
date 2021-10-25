package com.dbbl.mrt.recharge.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dbbl.mrt.recharge.entity.MPUser;
import com.dbbl.mrt.recharge.model.AuthenticationRequest;
import com.dbbl.mrt.recharge.model.TokenResponse;
import com.dbbl.mrt.recharge.service.IUserService;
import com.dbbl.mrt.recharge.util.JwtUtil;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	JwtUtil jwtUtil;
	@Autowired
	IUserService userService;

	@RequestMapping(value = "/token", method = RequestMethod.POST)
	public TokenResponse getToken(@RequestBody AuthenticationRequest authenticationRequest) {

		TokenResponse response = new TokenResponse();

		log.info("Token Requested by User: " + authenticationRequest.getUsername());

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));

		} catch (BadCredentialsException e) {
			response.setStatus(1);
			response.setRspCode("ACC-001");
			response.setRspMsg("Username/password invalid");
			return response;
		}

		MPUser mpUser = userService.findByUserName(authenticationRequest.getUsername()).get();
		if (StringUtils.equals(authenticationRequest.getSecret(), mpUser.getSecretKey()) == false) {
			response.setStatus(1);
			response.setRspCode("ACC-002");
			response.setRspMsg("Secret Key invalid");
			return response;
		}

		String jwt = jwtUtil.generateToken(mpUser);

		response.setToken(jwt);

		return response;
	}
}
