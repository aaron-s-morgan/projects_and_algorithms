package com.aaronmorgan.food.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.aaronmorgan.food.models.Restaurant;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long>{
	List<Restaurant>findAll();
}
