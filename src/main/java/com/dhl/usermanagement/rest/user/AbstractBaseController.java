package com.dhl.usermanagement.rest.user;


import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.dhl.usermanagement.util.ServiceResponse;

public class AbstractBaseController {

	protected Locale locale = Locale.ENGLISH;

	@Autowired
	protected HttpServletRequest request;

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ServiceResponse> handleExceptions(Exception e) {
		ServiceResponse response =  new ServiceResponse(e.getMessage());
		return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
