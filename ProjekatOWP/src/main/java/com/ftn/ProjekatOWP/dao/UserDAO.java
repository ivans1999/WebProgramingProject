package com.ftn.ProjekatOWP.dao;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.ftn.ProjekatOWP.model.User;

public interface UserDAO {
	
	
	public User findOne(String username);

	public User findOne(String username, String password);
	
	public List<User> find(String username,String name,String lastname,String email,String address,String phoneNumber,
			String gender, LocalDateTime dateOfBirth, Date registrationDate, Boolean administrator);
	
	public void save(User user);


}
