package com.dbbl.mrt.recharge.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@RequestMapping("/test")
	public String admin() {
		return "Welcome Admin";
	}
}
