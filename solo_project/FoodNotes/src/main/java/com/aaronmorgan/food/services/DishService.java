package com.aaronmorgan.food.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aaronmorgan.food.models.Dish;
import com.aaronmorgan.food.models.Restaurant;
import com.aaronmorgan.food.models.User;
import com.aaronmorgan.food.repositories.DishRepository;

@Service
public class DishService {
	@Autowired
	private DishRepository dRepo;
	
	public DishService(DishRepository dRepo) {
		this.dRepo = dRepo;
	}
    
	//Display All
	public List<Dish> getAllDishes(){
		return this.dRepo.findAll();
	}
	
	//Display One
	public Dish getSingleDish(Long id) {
		return this.dRepo.findById(id).orElse(null);
	}
	
	//Create
	public Dish createDish(Dish dish) {
		return this.dRepo.save(dish);
	}
	
	// Delete
	public void deleteDish(Long id) {
		this.dRepo.deleteById(id);
	}
	
	// Update
	public Dish updateDish(Dish dish) {
		return this.dRepo.save(dish);
	}
	//Create Manually
	public void addDish(Restaurant server, String dishName, String dishPic) {
		this.dRepo.save(new Dish(server, dishName, dishPic));
	}
	//Favorite Dish
	public void favoriteDish(User user, Dish dish) {
		List<User> favoritors = dish.getFavoritors();
		favoritors.add(user);
		this.dRepo.save(dish);
	}
	//UnFavorite Dish
	public void unfavoriteDish(User user, Dish dish) {
		List<User> favoritors = dish.getFavoritors();
		favoritors.remove(user);
		this.dRepo.save(dish);
	}
	//profile pic
	public void uploadDishPic(Dish dish, String image_url) {
		dish.setDishPic(image_url);
		this.dRepo.save(dish);
	}
}
