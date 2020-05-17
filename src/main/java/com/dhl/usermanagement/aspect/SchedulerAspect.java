package com.dhl.usermanagement.aspect;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.dhl.usermanagement.repository.job.IJobRepository;

@Aspect
@Component
public class SchedulerAspect {

	@Value("${user.count.job.interval}")
	private String userJobScheduleInSeconds;

	@Autowired
	IJobRepository jobRepository;

	@Around("allScheduledJob()")
	public Object runJobOnlyOnceInMultipleInstance(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("inside aop");
		String jobId = new StringBuilder(
				joinPoint.getTarget().toString().substring(0, joinPoint.getTarget().toString().indexOf("@")))
						.append(".").append(joinPoint.getSignature().getName()).toString();
		if (!jobAlreadyExecuted(jobId)) {
			jobRepository.createJobEntry(jobId);
			jobRepository.updateJobEntry(jobId);

		} else {
			throw new RuntimeException("job already executed");
		}
		return joinPoint.proceed();
	}
	
	private boolean jobAlreadyExecuted(String jobId) {
		Date executionTime = jobRepository.getJobEntry(jobId);
		if (executionTime == null) {
			return false;
		}
		long diff = new Date().getTime() - executionTime.getTime();
		long diffSeconds = diff / 1000;
		System.out.println("job id==" + jobId);
		long secondsToevalute = getThreshholdTime(jobId);
		System.out.println("secondsToevalute==" + secondsToevalute);
		return diffSeconds < secondsToevalute / 1000;
	}

	private long getThreshholdTime(String jobId) {
		long result;
		switch (jobId) {
		case "com.dhl.usermanagement.jobs.UserJob.getUsersCount":
			result = Long.parseLong(userJobScheduleInSeconds);
			break;
		default:
			result = Long.parseLong(userJobScheduleInSeconds);
		}
		return result;
	}
	
	@Pointcut("execution(@org.springframework.scheduling.annotation.Scheduled * *(..))")
	public void allScheduledJob() {}
}
