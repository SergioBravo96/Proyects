package com.dinner.validate;

import org.springframework.stereotype.Component;

@Component
public class OrderIn {

	private Long id_order;
	private String email;
	private String[] saucer;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String[] getSaucer() {
		return saucer;
	}
	public void setSaucer(String[] saucer) {
		this.saucer = saucer;
	}
	public Long getId_order() {
		return id_order;
	}
	public void setId_order(Long id_order) {
		this.id_order = id_order;
	}
	
	
}
