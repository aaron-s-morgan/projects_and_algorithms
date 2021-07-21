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
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;


@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @Size(min=2, message="Longer first name must be given")
    private String firstName;
    @Size(min=2, message="Longer last name must be given")
    private String lastName;
    
    @Email
    private String email;
    
    @Size(min=8, message="passwords have a min of 8 characters")
	private String password;
    @Transient
    private String passwordConfirmation;
    
    private boolean darkMode;
    
    @Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
    
    @OneToMany(mappedBy="creator", fetch=FetchType.LAZY)
	private List<Restaurant> restaurantsCreated;
    
    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
		name="users_restaurants",
		joinColumns = @JoinColumn(name="user_id"),
		inverseJoinColumns = @JoinColumn(name="restaurant_id")
	)
    private List<Restaurant> RestaurantsLiked;
    
	@ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
		name="users_dishes",
		joinColumns = @JoinColumn(name="user_id"),
		inverseJoinColumns = @JoinColumn(name="dish_id")
	)
    private List<Dish> favoriteDishes;
    
    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
		name="follows",
		joinColumns = @JoinColumn(name="user_id"),
		inverseJoinColumns = @JoinColumn(name="following_id")
	)
    private List<User> followers;
    
    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
		name="follows",
		joinColumns = @JoinColumn(name="following_id"),
		inverseJoinColumns = @JoinColumn(name="user_id")
	)
    private List<User> following;
    
    @OneToMany(mappedBy="author", fetch=FetchType.LAZY)
   	private List<Note> notesPosted;
    
    private String profilePic;
    
	public User() {
    }
    
    
    public List<Restaurant> getRestaurantsCreated() {
		return restaurantsCreated;
	}


	public void setRestaurantsCreated(List<Restaurant> restaurantsCreated) {
		this.restaurantsCreated = restaurantsCreated;
	}


	public List<Restaurant> getRestaurantsLiked() {
		return RestaurantsLiked;
	}


	public void setRestaurantsLiked(List<Restaurant> restaurantsLiked) {
		RestaurantsLiked = restaurantsLiked;
	}


	public List<User> getFollowers() {
		return followers;
	}


	public void setFollowers(List<User> followers) {
		this.followers = followers;
	}


	public List<User> getFollowing() {
		return following;
	}


	public void setFollowing(List<User> following) {
		this.following = following;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}


	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}

	public boolean isDarkMode() {
		return darkMode;
	}


	public void setDarkMode(boolean darkMode) {
		this.darkMode = darkMode;
	}


	public String getProfilePic() {
		return profilePic;
	}


	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
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
	
    public List<Dish> getFavoriteDishes() {
		return favoriteDishes;
	}


	public void setFavoriteDishes(List<Dish> favoriteDishes) {
		this.favoriteDishes = favoriteDishes;
	}

	
	public List<Note> getNotesPosted() {
		return notesPosted;
	}

	public void setNotesPosted(List<Note> notesPosted) {
		this.notesPosted = notesPosted;
	}

	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
}