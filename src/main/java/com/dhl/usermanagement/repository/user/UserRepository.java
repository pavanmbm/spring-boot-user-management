package com.dhl.usermanagement.repository.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.dhl.usermanagement.model.User;

@Repository
public class UserRepository implements IUserRepository{

	@Autowired
	JdbcTemplate jdbcTemplate;

	public int createUser(User user) {
		return jdbcTemplate.update("insert into users (username, first_name, last_name,email,phone) " + "values(?,?, ?,?,?)",
				new Object[] { user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail(),
						user.getPhone() });
	}

	public int getAllUserCount() {
		return jdbcTemplate.queryForObject("select count(*) from users", Integer.class);
	}

	@Cacheable(value="userFindCache")
	public User getUser(String username) {
		try {
		return jdbcTemplate.queryForObject("select * from users where username=?", new Object[] { username },
				new UserRowMapper());
		}
		catch(EmptyResultDataAccessException ex) {
			throw new RuntimeException("No User Found");
		}
	}
}

class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rownum) throws SQLException {
		User user = new User();
		user.setUsername(rs.getString("username"));
		user.setFirstName(rs.getString("first_name"));
		user.setLastName(rs.getString("last_name"));
		user.setEmail(rs.getString("email"));
		user.setPhone(rs.getLong("phone"));
		return user;
	}

}