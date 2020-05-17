package com.dhl.usermanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableScheduling
@EnableWebSecurity
@EnableAspectJAutoProxy
@EnableCaching
public class UserManagementApplication {
	public static void main(String[] args) {
		SpringApplication.run(UserManagementApplication.class, args);
	}

}
