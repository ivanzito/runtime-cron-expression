package br.com.intelie.controller.jobs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.intelie.model.Job;
import br.com.intelie.service.JobServiceImpl;

@RestController
public class CronController {

	@Autowired
	private JobServiceImpl jobService;
	
	@RequestMapping(value = "/jobs", produces = "application/json", method = RequestMethod.POST)
	public ResponseEntity<?> postJob(@RequestBody(required = true) Job job) {
		if(jobService.addJob(job)) {
			return ResponseEntity.ok().build();	
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@RequestMapping(value = "/jobs/{jobName}", produces = "application/json", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteJob(@PathVariable("jobName") String jobName) {
		if(jobService.removeJob(jobName)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@RequestMapping(produces = "application/json", method = RequestMethod.GET)
	public ResponseEntity<List<Job>> listJob() {
		return ResponseEntity.ok(jobService.listJob());
	}
}
