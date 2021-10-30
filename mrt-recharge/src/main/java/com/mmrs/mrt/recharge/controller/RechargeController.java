package com.mmrs.mrt.recharge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mmrs.mrt.recharge.model.FunctionResult;
import com.mmrs.mrt.recharge.service.IRechargeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/recharge")
public class RechargeController extends RootController {

	@Autowired
	IRechargeService rechargeService;

	@RequestMapping(method = RequestMethod.GET, value = "/info")
	FunctionResult getRechargeInfo(@RequestParam String id) {

		log.info("Entrering getRechargeInfo GET");
		log.info("Param: " + id);

		FunctionResult functionResult = rechargeService.getRechargeInfo();
		return functionResult;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/info")
	ResponseEntity<?> getRechargeInfoPost(@RequestBody FunctionResult params) {

		log.info("Entrering getRechargeInfo POST");
		log.info("Param: " + params);
//		return ResponseEntity.ok(params);

		return ResponseEntity.ok(params);
	}
}
