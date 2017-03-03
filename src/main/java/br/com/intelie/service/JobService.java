package br.com.intelie.service;

import java.util.List;

import br.com.intelie.model.Job;

public interface JobService {

	 List<Job> listJob();
	 
	 boolean removeJob(String jobName);
	 
	 boolean addJob(Job job);
}
