package com.aaronmorgan.food.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="restaurants")
public class Restaurant {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    private String restaurantName;

	@NotBlank
	private String cuisine;
    
	private String restaurantPic;
	
    @Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
    

	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User creator;
   
	
    public List<Dish> getRestaurantDishes() {
		return restaurantDishes;
	}


	public void setRestaurantDishes(List<Dish> restaurantDishes) {
		this.restaurantDishes = restaurantDishes;
	}

	@ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
		name="users_restaurants",
		joinColumns = @JoinColumn(name="restaurant_id"),
		inverseJoinColumns = @JoinColumn(name="user_id")
	)
    private List<User> likers;

    @OneToMany(mappedBy="server", fetch=FetchType.LAZY)
	private List<Dish> restaurantDishes;
    
    
	public Restaurant() {

	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getRestaurantName() {
		return restaurantName;
	}


	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}


	public String getCuisine() {
		return cuisine;
	}


	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}


	public String getRestaurantPic() {
		return restaurantPic;
	}


	public void setRestaurantPic(String restaurantPic) {
		this.restaurantPic = restaurantPic;
	}


	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public List<User> getLikers() {
		return likers;
	}

	public void setLikers(List<User> likers) {
		this.likers = likers;
	}
    
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}
}
   