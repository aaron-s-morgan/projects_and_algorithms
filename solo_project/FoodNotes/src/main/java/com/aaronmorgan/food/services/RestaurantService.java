package com.aaronmorgan.food.services;

import java.util.List;

import org.springframework.stereotype.Service;


import com.aaronmorgan.food.models.Restaurant;
import com.aaronmorgan.food.models.User;
import com.aaronmorgan.food.repositories.RestaurantRepository;

@Service
public class RestaurantService {
	private final RestaurantRepository rRepo;
    
    public RestaurantService(RestaurantRepository rRepo) {
        this.rRepo = rRepo;
    }
    
	//Display All
	public List<Restaurant> getAllRestaurants(){
		return this.rRepo.findAll();
	}
	
	//Display One
	public Restaurant getSingleRestaurant(Long id) {
		return this.rRepo.findById(id).orElse(null);
	}
	
	//Create
	public Restaurant createRestaurant(Restaurant restaurant) {
		return this.rRepo.save(restaurant);
	}
	
	// Delete
	public void deleteRestaurant(Long id) {
		this.rRepo.deleteById(id);
	}
	
	// Update
	public Restaurant updateRestaurant(Restaurant restaurant) {
		return this.rRepo.save(restaurant);
	}
	//Like Idea
	public void likeRestaurant(User user, Restaurant restaurant) {
		List<User> likers = restaurant.getLikers();
		likers.add(user);
		this.rRepo.save(restaurant);
	}
	//Unlike Idea
	public void unlikeRestaurant(User user, Restaurant restaurant) {
		List<User> likers = restaurant.getLikers();
		likers.remove(user);
		this.rRepo.save(restaurant);
	}
	//profile pic
	public void uploadRestaurantPic(Restaurant restaurant, String image_url) {
		restaurant.setRestaurantPic(image_url);
		this.rRepo.save(restaurant);
	}
}
