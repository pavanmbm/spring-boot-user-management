package com.dhl.usermanagement.repository.job;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JobRepository implements IJobRepository{

	@Autowired
	JdbcTemplate jdbcTemplate;

	
	public void createJobEntry(String jobId){
		jdbcTemplate.update("insert ignore into schedule_jobs(job_id,executed_at) values (?,CURRENT_TIMESTAMP)",new Object[]{jobId});
	}

	public void updateJobEntry(String jobId) {
		jdbcTemplate.update("update schedule_jobs set executed_at=CURRENT_TIMESTAMP where job_id=?", new Object[]{jobId});
	}

	public Date getJobEntry(String jobId) {
		try{
		return jdbcTemplate.queryForObject("select executed_at from schedule_jobs where job_id=?", new Object[] { jobId },Timestamp.class);
		}
		catch(EmptyResultDataAccessException ex) {
			return null;
		}
	}
}
