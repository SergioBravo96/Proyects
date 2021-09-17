package com.dinner.validate;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class Detail {
	
	private Long id;
	private Long SubOrder;
	private String email;
	private String date;
	private String municipality;
	private String neighborhoodName;
	private String street;
	private String num_ex;
	private List<Map<String,Object>> saucers;
	private Integer subTotal;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getMunicipality() {
		return municipality;
	}
	public void setMunicipality(String municipality) {
		this.municipality = municipality;
	}
	public String getNeighborhoodName() {
		return neighborhoodName;
	}
	public void setNeighborhoodName(String neighborhoodName) {
		this.neighborhoodName = neighborhoodName;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getNum_ex() {
		return num_ex;
	}
	public void setNum_ex(String num_ex) {
		this.num_ex = num_ex;
	}

	public List<Map<String, Object>> getSaucers() {
		return saucers;
	}
	public void setSaucers(List<Map<String, Object>> saucers) {
		this.saucers = saucers;
	}
	public Integer getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(Integer subTotal) {
		this.subTotal = subTotal;
	}
	public Long getSubOrder() {
		return SubOrder;
	}
	public void setSubOrder(Long subOrder) {
		SubOrder = subOrder;
	}
	
	

}
