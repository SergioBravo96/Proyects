package com.dinner.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dinner.models.Food;
import com.dinner.services.FoodService;

@RestController
@RequestMapping("/food")
public class FoodController {

	@Autowired
	FoodService foodService;
	
	@GetMapping()
	public ArrayList<Food> getFood(){
		return foodService.getFood();
	}
	
	@PostMapping()
	public Food saveFood(@RequestBody Food food) {
		return foodService.saveFood(food);
	}
}
