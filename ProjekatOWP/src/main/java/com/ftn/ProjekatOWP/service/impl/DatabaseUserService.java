package com.ftn.ProjekatOWP.service.impl;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.ProjekatOWP.dao.UserDAO;
import com.ftn.ProjekatOWP.model.Book;
import com.ftn.ProjekatOWP.model.User;
import com.ftn.ProjekatOWP.service.UserService;

@Service
public class DatabaseUserService implements UserService{
	
	
	@Autowired
	private UserDAO userDAO;
	
	
	@Override
	public User findOne(String username) {
		return userDAO.findOne(username);
	}
	
	@Override
	public User findOne1(String username) {
		return userDAO.findOne1(username);
	}
	
	@Override
	public User findOne2(String username) {
		return userDAO.findOne2(username);
	}
	

	@Override
	public User findOne(String username, String password) {
		return userDAO.findOne(username, password);
	}
	
	@Override
	public User findOne3(String username, String password) {
		return userDAO.findOne3(username, password);
	}
	
	@Override
	public User save(User user) {
		userDAO.save(user);
		return user;
	}
	
	
	  @Override 
	  public List<User> find(String username,String name,String lastname,String email,String address,String phoneNumber, 
			  					String gender, LocalDateTime dateOfBirth, Date registrationDate, Boolean administrator, Boolean block) {
	  
		return userDAO.find(username, name, lastname, email, address, phoneNumber,
					  	gender, dateOfBirth ,registrationDate, administrator, block); 
	  
	  }
	  
	  
	  @Override
		public User update(User user) {
			userDAO.update(user);
			return user;
		}
	  
	  @Override
		public User update1(User user) {
			userDAO.update1(user);
			return user;
		}
	 
}