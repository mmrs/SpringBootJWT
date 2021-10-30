
package com.mmrs.mrt.recharge.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mmrs.mrt.recharge.model.FunctionResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
//public class AppErrorController implements ErrorController{
public class BaseController extends RootController implements ErrorController {

	@RequestMapping("/")
	public String root() {
		return home();
	}

	@RequestMapping("/home")
	public String home() {
		return "Welcome to MRT Recharge API Gateway";
	}

	@RequestMapping("/app-error")
	public ResponseEntity<?> handleError(HttpServletRequest request) {

		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		Object message = request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
		Object exceptionType = request.getAttribute(RequestDispatcher.ERROR_EXCEPTION_TYPE);

		log.error("Error occcured with with Status:" + status);

		FunctionResult functionResult = new FunctionResult();
		functionResult.setValid(false);
		if (status != null) {
			functionResult.setMsgCode(String.valueOf(status));
			functionResult.setMsgBody(String.valueOf(message));
			functionResult.setData("exception", String.valueOf(exceptionType));
		}

		return ResponseEntity.ok(functionResult);
	}
}