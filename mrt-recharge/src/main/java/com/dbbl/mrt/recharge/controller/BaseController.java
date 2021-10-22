
package com.dbbl.mrt.recharge.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//public class AppErrorController implements ErrorController{
public class BaseController extends RootController {

	@RequestMapping("/")
	public String root() {
		return home();
	}

	@RequestMapping("/home")
	public String home() {
		return "Welcome to MRT Recharge API Gateway";
	}

	@RequestMapping("/app-error")
	public String handleError(HttpServletRequest request) {
//		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
//		logger.error("Error occcured with with Status:" + status);
		return "error";
	}
}