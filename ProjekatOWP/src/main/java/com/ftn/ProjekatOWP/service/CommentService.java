package com.ftn.ProjekatOWP.service;

import java.util.List;

import com.ftn.ProjekatOWP.model.Comment;


public interface CommentService {

	
	Comment findOne(Long id);
	
	List<Comment> findAll(Long bookId);
	
	List<Comment> findAll();
	
	Comment save(Comment comment);
	
	Comment update(Comment comment);
}
