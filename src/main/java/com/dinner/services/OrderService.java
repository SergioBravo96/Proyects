package com.dinner.services;



import java.util.ArrayList;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dinner.models.Order;
import com.dinner.repositories.OrderRepository;

@Service
public class OrderService {

	@Autowired
	OrderRepository orderRepository;
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(OrderService.class);
	public void saveOrder(Order order) {
		orderRepository.save(order);
		
	}
	
	public ArrayList<Order> getSaucer(String saucer){
		return orderRepository.findBySaucer(saucer);
	}
	
	public Optional<Order> getId(Long id){
		return orderRepository.findById(id);
	}
}
