package com.ftn.ProjekatOWP.service.impl;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.ProjekatOWP.model.User;
import com.ftn.ProjekatOWP.service.UserService;
import com.ftn.ProjekatOWP.dao.UserDAO;

@Service
public class DatabaseUserService implements UserService{
	
	
	@Autowired
	private UserDAO userDAO;
	
	
	@Override
	public User findOne(String username) {
		return userDAO.findOne(username);
	}

	@Override
	public User findOne(String username, String password) {
		return userDAO.findOne(username, password);
	}
	
	@Override
	public User save(User user) {
		userDAO.save(user);
		return user;
	}
	
	@Override
	public List<User> find(String username,String name,String lastname,String email,String address,String phoneNumber,
			String gender, LocalDateTime dateOfBirth, Date registrationDate, Boolean administrator) {
	
		return userDAO.find(username, name, lastname, email, address, phoneNumber, gender, dateOfBirth ,registrationDate, administrator);
	}

}
