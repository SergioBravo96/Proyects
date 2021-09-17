package com.dinner.validate;

import org.springframework.stereotype.Component;

@Component
public class Issues {
	private String saucer;
	private boolean status;

	public String getSaucer() {
		return saucer;
	}
	public void setSaucer(String saucer) {
		this.saucer = saucer;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}

	
	
}
