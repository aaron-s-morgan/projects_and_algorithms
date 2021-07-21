package com.aaronmorgan.food.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.aaronmorgan.food.models.Note;

public interface NoteRepository extends CrudRepository<Note, Long>{
	List<Note>findAll();
}
