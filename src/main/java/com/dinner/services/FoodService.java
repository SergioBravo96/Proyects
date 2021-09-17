package com.dinner.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dinner.models.Food;
import com.dinner.repositories.FoodRepository;

@Service
public class FoodService {

	@Autowired
	FoodRepository foodRepository;
	
	public ArrayList<Food> getFood(){
		return (ArrayList<Food>) foodRepository.findAll();
	}
	
	public Food saveFood(Food food) {
		return foodRepository.save(food);
	}
	
	public Optional<Food> getById(String id) {
		return foodRepository.findById(id);
	}
}
