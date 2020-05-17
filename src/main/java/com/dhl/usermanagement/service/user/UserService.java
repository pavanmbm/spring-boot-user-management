package com.dhl.usermanagement.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhl.usermanagement.model.User;
import com.dhl.usermanagement.repository.user.IUserRepository;

@Service
public class UserService implements IUserService{

	@Autowired
	IUserRepository userRepository;
	
	public String createUser(User user) {
		StringBuilder message =new StringBuilder();
		int updateCount = userRepository.createUser(user);
		if(updateCount>0){
			message.append("user created successfully");
		}
		else{
			message.append("user creation failed");
		}
		return message.toString();
	}
	
	public int getAllUserCount() {
		return userRepository.getAllUserCount();
	}
	
	public User getUser(String username) {
		return userRepository.getUser(username);
	}
}
