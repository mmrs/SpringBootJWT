package com.dbbl.mrt.recharge.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dbbl.mrt.recharge.entity.MPUser;
import com.dbbl.mrt.recharge.model.FunctionResult;
import com.dbbl.mrt.recharge.service.IUserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

	@Autowired
	IUserService userService;

	@RequestMapping(method = RequestMethod.GET, value = "/{username}")
	public ResponseEntity<?> getUserInfo(@PathVariable("username") String userName) {

		Optional<MPUser> user = userService.findByUserName(userName);
		log.info("User Details: " + user);
		return ResponseEntity.ok(user);

	}

	@RequestMapping(method = RequestMethod.POST, value = "/{username}")
	public ResponseEntity<?> getUserInfoProcedure(@PathVariable("username") String userName) {
		FunctionResult user = userService.getDataByProcedure(userName);
		log.info("Procedure User Details: " + user);
		return ResponseEntity.ok(user);

	}
}
