package com.dinner.repositories;

import org.springframework.data.repository.CrudRepository;

import com.dinner.models.Food;

public interface FoodRepository extends CrudRepository<Food, String>{

}
