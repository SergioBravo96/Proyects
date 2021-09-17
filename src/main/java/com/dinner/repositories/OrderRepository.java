package com.dinner.repositories;


import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.dinner.models.Order;

public interface OrderRepository extends CrudRepository<Order, Long>{
	
	public abstract ArrayList<Order> findBySaucer(String saucer);

}
