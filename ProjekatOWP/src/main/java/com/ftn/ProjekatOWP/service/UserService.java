package com.ftn.ProjekatOWP.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ftn.ProjekatOWP.model.Book;
import com.ftn.ProjekatOWP.model.User;



public interface UserService {
	
	public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	
	User findOne(String username);
	
	User findOne1(String username);
	
	User findOne2(String username);
	
	User findOne(String username, String password);
	
	User findOne3(String username, String password);
	
	User save(User user);
	
	
	  List<User> find(String username,String name,String lastname,String
	  email,String address,String phoneNumber, String gender, LocalDateTime
	  dateOfBirth, Date registrationDate, Boolean administrator, Boolean block);
	  
	  User update(User user);
	  
	  User update1(User user);
	 
}
