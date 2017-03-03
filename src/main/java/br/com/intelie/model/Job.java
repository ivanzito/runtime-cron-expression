package br.com.intelie.model;

import java.util.Objects;

public class Job {

	private String msg;
	
	private String name;
	
	private String cron;

	public Job() {
		super();
	}
	
	public Job(String name) {
		this.name = name;
	}
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCron() {
		return cron;
	}

	public void setCron(String cron) {
		this.cron = cron;
	}
	
	@Override
	public String toString() {
		return Objects.toString(this.name) + " " + 
				Objects.toString(this.cron) + " " + 
				Objects.toString(this.msg);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj != null && !(obj instanceof Job)) {
			return false;
		}
		Job job = (Job) obj;
		return Objects.equals(job.getName(), this.getName());
	}
	
	@Override
	public int hashCode() {
		return Objects.hashCode(this.name);
	}
}
