package br.com.intelie.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import br.com.intelie.model.Job;

@Service
public class JobServiceImpl implements JobService {

	@Autowired
	private ThreadPoolTaskScheduler threadPoolTaskScheduler;

	private Map<Job, ScheduledFuture<?>> scheduleFuture = new ConcurrentHashMap<>();
	
	private static final Logger LOGGER = Logger.getLogger(JobServiceImpl.class);
	
	public boolean addJob(Job job) {
		
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("Adding the job " + job);
		}
		
		if(this.scheduleFuture.get(job) != null) {
			this.removeJob(job.getName());
		}
		
		Trigger trigger;
		
		try{
			trigger = new CronTrigger(job.getCron());
		
		} catch(RuntimeException e) {
			LOGGER.warn("Provavel expressao CRON invalida " + job.getCron());
			return false;
		}
		
		this.scheduleFuture.put(job, threadPoolTaskScheduler.schedule(new Runnable() {
			@Override
			public void run() {
				System.out.println(job.getMsg());
			}
		}, trigger));
		
		return true;
	}
	
	public boolean removeJob(String jobName) {
		
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("Removing the job " + jobName);
		}
		
		Job job = new Job(jobName);
		ScheduledFuture<?> scheduledFuture = this.scheduleFuture.get(job);
		
		if(scheduledFuture == null) {
			return false;
		}
		scheduledFuture.cancel(true);
		this.scheduleFuture.remove(job);
		
		return true;
	}
	
	public List<Job> listJob() {
		
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("Listing jobs...");
		}
		
		return this.scheduleFuture.entrySet()
				.parallelStream()
				.map(f-> f.getKey())
				.collect(Collectors.toList());
	}
}
