package com.ftn.ProjekatOWP.dao;

import java.util.List;

import com.ftn.ProjekatOWP.model.Comment;
import com.ftn.ProjekatOWP.model.User;



public interface CommentDAO {
	
	public Comment findOne(Long id);
	
	public List<Comment> findAll(Long id);
	
	public List<Comment> findAll();
	
	public int save(Comment comment);
	
	public int update(Comment comment);

}
