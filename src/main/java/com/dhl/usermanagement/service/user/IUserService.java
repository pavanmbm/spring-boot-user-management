package com.dhl.usermanagement.service.user;

import com.dhl.usermanagement.model.User;

public interface IUserService {

	public String createUser(User user);
	
	public int getAllUserCount();
	
	public User getUser(String username);
}
