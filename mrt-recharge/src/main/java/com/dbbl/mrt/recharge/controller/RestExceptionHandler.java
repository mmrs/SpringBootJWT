package com.dbbl.mrt.recharge.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.dbbl.mrt.recharge.model.FunctionResult;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		FunctionResult functionResult = new FunctionResult();
		functionResult.setValid(false);
		functionResult.setMsgCode(String.valueOf(status.value()));
		functionResult.setMsgBody("Method Not Allowed");
		return ResponseEntity.ok(functionResult);
	}
}
