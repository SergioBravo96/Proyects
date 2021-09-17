package com.dinner.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dinner.models.Customers;
import com.dinner.repositories.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository customerRepository;
	
	public ArrayList<Customers> getCustomer(){
		return (ArrayList<Customers>) customerRepository.findAll();
	}
	
	public Customers saveCustomer(Customers customers) {
		return customerRepository.save(customers);
	}
	
	public Optional<Customers> getById(String customer) {
		return customerRepository.findById(customer);
	}
}
