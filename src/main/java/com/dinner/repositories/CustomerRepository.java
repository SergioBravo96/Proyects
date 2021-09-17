package com.dinner.repositories;

import org.springframework.data.repository.CrudRepository;

import com.dinner.models.Customers;

public interface CustomerRepository extends CrudRepository<Customers, String> {

}
