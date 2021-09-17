package com.dinner.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dinner.models.Customers;
import com.dinner.services.CustomerService;
import com.dinner.validate.Detail;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	CustomerService customerService;
	@Autowired
	Detail detail;

	@GetMapping()
	public ArrayList<Customers> getCustomers() {
		return customerService.getCustomer();

	}

	@PostMapping()
	public String saveCustomers(@RequestBody Customers customers) {

		customerService.saveCustomer(customers);
		return "Cliente Resguardado Exitosamente";
	}

}
