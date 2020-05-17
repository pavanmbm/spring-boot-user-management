package com.dhl.usermanagement.rest.user;

import java.util.Arrays;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhl.usermanagement.model.User;
import com.dhl.usermanagement.service.user.IUserService;
import com.dhl.usermanagement.util.AppConstants;
import com.dhl.usermanagement.util.ServiceResponse;

@RestController
@RequestMapping(value="api/v1/users")
public class UserController extends AbstractBaseController {
	
	Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	IUserService userService;
	
	@PostMapping
	public ResponseEntity<ServiceResponse> createUser(@RequestBody User user, HttpServletResponse response){
		String message =  userService.createUser(user);
		return new ResponseEntity<>(new ServiceResponse(message),
				HttpStatus.OK);
	}
	
 	@Cacheable(value="userFindCache")
	@GetMapping(value="/{username}",produces="application/json")
	public ResponseEntity<ServiceResponse> getUser(@PathVariable("username") String username, HttpServletResponse response){
		User user =  userService.getUser(username);
		return new ResponseEntity<>(new ServiceResponse(Arrays.asList(user), AppConstants.SUCCESS),
				HttpStatus.OK);
	}
}
