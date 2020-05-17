package com.dhl.usermanagement.rest.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/")
public class WelcomeController {

	@GetMapping
	public String welcome() {
		return "<h1>welcome admin</h1>";
	}
}
