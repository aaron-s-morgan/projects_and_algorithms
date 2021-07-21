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
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="dishes")
public class Dish {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    private String dishName;
    
    @Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
    
    private String dishPic;
    
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="restaurant_id")
    	private Restaurant server;
	
	@ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
		name="users_dishes",
		joinColumns = @JoinColumn(name="dish_id"),
		inverseJoinColumns = @JoinColumn(name="user_id")
	)
    private List<User> favoritors;

	@OneToMany(mappedBy="dishNoted", fetch=FetchType.LAZY)
   	private List<Note> dishNoted;
	
	public Dish() {

	}
	public Dish(Restaurant server, String dishName, String dishPic) {
    	this.server = server;
    	this.dishName = dishName;
    	this.dishPic = dishPic;
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getDishName() {
		return dishName;
	}


	public void setDishName(String dishName) {
		this.dishName = dishName;
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


	public String getDishPic() {
		return dishPic;
	}
	public void setDishPic(String dishPic) {
		this.dishPic = dishPic;
	}
	public Restaurant getServer() {
		return server;
	}


	public void setServer(Restaurant server) {
		this.server = server;
	}
	
	public List<User> getFavoritors() {
		return favoritors;
	}
	public void setFavoritors(List<User> favoritors) {
		this.favoritors = favoritors;
	}
	public List<Note> getDishNoted() {
		return dishNoted;
	}
	public void setDishNoted(List<Note> dishNoted) {
		this.dishNoted = dishNoted;
	}
	
	
}
