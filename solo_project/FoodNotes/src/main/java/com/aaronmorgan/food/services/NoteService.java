package com.aaronmorgan.food.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aaronmorgan.food.models.Dish;
import com.aaronmorgan.food.models.Note;
import com.aaronmorgan.food.models.User;
import com.aaronmorgan.food.repositories.NoteRepository;

@Service
public class NoteService {
	@Autowired
	private NoteRepository nRepo;
	
	public NoteService(NoteRepository nRepo) {
		this.nRepo = nRepo;
	}
	
	
	//Create
	public Note createNote(Note note) {
		return this.nRepo.save(note);
	}
	
	
	//Create Manually
	public void addNote(User author, Dish dishNoted, String content) {
		this.nRepo.save(new Note(author, dishNoted, content));
	}
}
