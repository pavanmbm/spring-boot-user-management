package com.dhl.usermanagement.repository.user;

import com.dhl.usermanagement.model.User;

public interface IUserRepository {

	public int createUser(User user);

	public int getAllUserCount();

	public User getUser(String username);
}
