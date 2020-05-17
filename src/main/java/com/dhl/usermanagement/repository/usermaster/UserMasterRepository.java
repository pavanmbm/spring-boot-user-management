package com.dhl.usermanagement.repository.usermaster;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.dhl.usermanagement.model.UserMaster;

@Repository
public class UserMasterRepository implements IUserMasterRepository{

	@Autowired
	JdbcTemplate jdbcTemplate;

	
	public UserMaster getUser(String username) {
		try {
		return jdbcTemplate.queryForObject("select * from user_master where user_id=?", new Object[] { username },
				new UserMasterRowMapper());
		}
		catch(EmptyResultDataAccessException ex) {
			throw new RuntimeException("No User Found");
		}
	}
}

class UserMasterRowMapper implements RowMapper<UserMaster> {

	@Override
	public UserMaster mapRow(ResultSet rs, int rownum) throws SQLException {
		UserMaster user = new UserMaster();
		user.setUserId(rs.getString("user_id"));
		user.setPassword(rs.getString("pass_word"));
		return user;
	}

}