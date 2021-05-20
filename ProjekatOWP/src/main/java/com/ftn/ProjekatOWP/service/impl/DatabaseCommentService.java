package com.ftn.ProjekatOWP.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.ProjekatOWP.dao.CommentDAO;
import com.ftn.ProjekatOWP.model.Book;
import com.ftn.ProjekatOWP.model.Comment;
import com.ftn.ProjekatOWP.service.CommentService;


@Service
public class DatabaseCommentService  implements CommentService{

	
	@Autowired
	private CommentDAO commentDAO;
	
	
	@Override
	public Comment findOne(Long bookId) {
		return commentDAO.findOne(bookId);
	}
	
	@Override
	public List<Comment> findAll(Long bookId) {
		return commentDAO.findAll(bookId);
	}
	
	@Override
	public List<Comment> findAll() {
		return commentDAO.findAll();
	}
	
	@Override
	public Comment save(Comment comment) {
		commentDAO.save(comment);
		return comment;
	}
	
	@Override
	public Comment update(Comment comment) {
		commentDAO.update(comment);
		return comment;
	}
}
