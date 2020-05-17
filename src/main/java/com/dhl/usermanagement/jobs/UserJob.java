package com.dhl.usermanagement.jobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.dhl.usermanagement.repository.user.IUserRepository;

@Component
public class UserJob {

	Logger logger = LoggerFactory.getLogger(UserJob.class);
	
	@Autowired
	IUserRepository userRepository;
	
	@Scheduled(initialDelayString="${user.count.job.inital.delay}",fixedDelayString="${user.count.job.fixed.delay}")
	public void getUsersCount(){
		logger.info("Total user count "+userRepository.getAllUserCount());
	}
	
}
