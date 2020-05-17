package com.dhl.usermanagement.repository.job;

import java.util.Date;

public interface IJobRepository {

	public void createJobEntry(String jobId);

	public void updateJobEntry(String jobId);

	public Date getJobEntry(String jobId);
}
