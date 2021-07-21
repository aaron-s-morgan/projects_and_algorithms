package com.aaronmorgan.food.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.aaronmorgan.food.models.Dish;

public interface DishRepository extends CrudRepository<Dish, Long>{
	List<Dish>findAll();
}
