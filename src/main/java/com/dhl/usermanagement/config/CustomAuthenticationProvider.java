package com.dhl.usermanagement.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.dhl.usermanagement.model.Role;
import com.dhl.usermanagement.model.UserMaster;
import com.dhl.usermanagement.repository.usermaster.IUserMasterRepository;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	IUserMasterRepository userRepository;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		UserMaster user = userRepository.getUser(authentication.getName());
		if(user !=null && authentication.getCredentials() != null && passwordMatch(authentication,user)) {
			List<Role> roles = new ArrayList<>();
			roles.add(new Role("ADMIN"));
			user.setAuthorities(roles);
			return new UsernamePasswordAuthenticationToken(user, user.getPassword(),user.getAuthorities());
		}
		else {
			throw new BadCredentialsException("user name or Password is not valid");
		}
		//return null;
	}

	private boolean passwordMatch(Authentication authentication,UserMaster user) {
		return passwordEncoder.matches(authentication.getCredentials().toString(), user.getPassword());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}

}
