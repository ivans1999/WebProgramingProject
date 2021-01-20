package com.ftn.ProjekatOWP.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ftn.ProjekatOWP.model.User;
import com.ftn.ProjekatOWP.service.UserService;


@Controller
@RequestMapping(value="/Users")
public class UserController {
	
	public static final String USER_KEY = "loggedInUser";
	
	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ServletContext servletContext;
	private String baseURL; 

	@PostConstruct
	public void init() {	
		baseURL = servletContext.getContextPath() + "/";			
	}
	
	
	@PostMapping(value="/Register")
	public ModelAndView register(@RequestParam String username, @RequestParam String name, @RequestParam String lastname, 
								@RequestParam String password, @RequestParam String repetedPassword, @RequestParam String email, 
								@RequestParam String address, @RequestParam String phoneNumber,  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateOfBirth,
								@RequestParam String gender, 
								HttpSession session, HttpServletResponse response) throws IOException {
		try {
			
			
			
				long yourmilliseconds = System.currentTimeMillis();
				
				SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm");    
			
				Date registrationDate = new Date(yourmilliseconds);
				
				System.out.println(sdf.format(registrationDate));
			
			
			// validacija
			User existUser = userService.findOne(username);
			if (existUser != null) {
				throw new Exception("Username already exists!");
			}
			if (username.equals("") || password.equals("")) {
				throw new Exception("Username or password must not be blank!");
			}
			if (!password.equals(repetedPassword)) {
				throw new Exception("Passwords do not match!");
			}
			if (email.equals("")) {
				throw new Exception("Email field must not be blank!");
			}
			if (name.equals("")) {
				throw new Exception("Name field must not be blank!");
			}
			if (lastname.equals("")) {
				throw new Exception("Lastname field must not be blank!");
			}
			if (address.equals("")) {
				throw new Exception("Address field must not be blank!");
			}
			if (phoneNumber.equals("")) {
				throw new Exception("Phone number field must not be blank!");
			}
			
			if (!gender.equals("male") && !gender.equals("female")) {
				throw new Exception("You must choose a gender!");
			}
			
			
			

			User user = new User( name, lastname, username, password,  email,  address, phoneNumber, gender, dateOfBirth,  registrationDate);
			userService.save(user);

			response.sendRedirect(baseURL);
			
			return null;
			
		} catch (Exception ex) {
	
			String poruka = ex.getMessage();
			if (poruka == "") {
				poruka = "Neuspešna registracija!";
			}

		
			ModelAndView rezultat = new ModelAndView("index.html");
			rezultat.addObject("poruka", poruka);
			
		

			return rezultat;
		}
	}
	
	@PostMapping(value="/Login")
	public ModelAndView postLogin(@RequestParam String username, @RequestParam String password, 
			HttpSession session, HttpServletResponse response) throws IOException {
		try {
			

			User user = userService.findOne(username, password);
			if (user == null) {
				throw new Exception("Invalid username or password!");
			}			


			session.setAttribute(UserController.USER_KEY, user);
			response.sendRedirect(baseURL);
			
			return null;
			
		} catch (Exception ex) {
			
			String poruka = ex.getMessage();
			if (poruka == "") {
				poruka = "Neuspešna prijava!";
			}
			
	
			ModelAndView rezultat = new ModelAndView("index.html");
			rezultat.addObject("poruka", poruka);

			return rezultat;
		}
	}

	@GetMapping(value="/Logout")
	public void logout(HttpSession session, HttpServletResponse response) throws IOException {
		
	
		session.invalidate();
		
		response.sendRedirect(baseURL);
	}

}

