package com.dhl.usermanagement.repository.usermaster;

import com.dhl.usermanagement.model.UserMaster;

public interface IUserMasterRepository {
	public UserMaster getUser(String userId);
}
