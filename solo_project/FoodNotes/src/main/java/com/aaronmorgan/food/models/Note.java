package com.aaronmorgan.food.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="notes")
public class Note {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name= "content", columnDefinition = "LONGTEXT")
	@NotBlank
    private String content;
	
    @Column(updatable=false)
    @DateTimeFormat
    private Date createdAt;
    @DateTimeFormat
    private Date updatedAt;
    
    @PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}
	
	 @ManyToOne(fetch=FetchType.LAZY)
	    @JoinColumn(name="user_id")
	    
	    private User author;
	 
	 @ManyToOne(fetch=FetchType.LAZY)
	    @JoinColumn(name="dish_id")
	    
	    private Dish dishNoted;

	public Note() {
	
	}
	
	public Note(User author, Dish dishNoted, String content) {
	    	this.author = author;
	    	this.dishNoted = dishNoted;
	    	this.content = content;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Dish getDishNoted() {
		return dishNoted;
	}

	public void setDishNoted(Dish dishNoted) {
		this.dishNoted = dishNoted;
	}
	 
}
