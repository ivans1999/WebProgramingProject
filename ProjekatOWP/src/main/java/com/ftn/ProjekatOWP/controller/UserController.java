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
import javax.servlet.http.HttpServletRequest;
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

import com.ftn.ProjekatOWP.model.Book;
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
	
	
	
	// ---------------------------- LISTA KORISNIKA
	
	
	  @GetMapping public ModelAndView index( 
			  			@RequestParam(required=false) String username, @RequestParam(required=false) String name,
			  			@RequestParam(required=false) String lastname, @RequestParam(required=false) String email, 
			  			@RequestParam(required=false) String address,
			  			@RequestParam(required=false) String phoneNumber, @RequestParam(required=false) String gender, 
			  			@RequestParam(required=false) LocalDateTime dateOfBirth,
			  			@RequestParam(required=false) Boolean administrator, @RequestParam(required=false) Date registrationDate,
			  			@RequestParam(required=false) Boolean block,
			  			HttpSession session, HttpServletResponse response) throws IOException {
	  
	  
		  User loggedInUser = (User) session.getAttribute(UserController.USER_KEY);
		  
		  if (loggedInUser == null || !loggedInUser.isAdministrator()) {
			  
			  response.sendRedirect(baseURL); 
			  return null; 
			  }
		  
		  if(username!=null && username.trim().equals("")) 
			  username=null;
		  
		  if(name!=null && name.trim().equals("")) 
			  name=null;
		  
		  if(lastname!=null && lastname.trim().equals("")) 
			  lastname=null;
		  
		  if(email!=null && email.trim().equals("")) 
			  email=null;
		  
		  if(address!=null && address.trim().equals("")) 
			  address=null;
		  
		  if(phoneNumber!=null && phoneNumber.trim().equals("")) 
			  phoneNumber=null;
		  
		  if(gender!=null && gender.trim().equals("")) 
			  gender=null;
	  
	  
	  
	  
	  List<User> users = userService.find(username, name, lastname, email, address,
			  								phoneNumber, gender, dateOfBirth ,registrationDate, administrator, block);
	  
	  
	  ModelAndView rezultat = new ModelAndView("users");
	  rezultat.addObject("users", users);
	  
	  return rezultat;
	 
	  }
	
	  
	// ---------------------------- DETAILS
	
	@GetMapping(value="/Details")
	public ModelAndView details(@RequestParam String username, 
			HttpSession session, HttpServletResponse response) throws IOException {		
		
		User loggedInUser = (User) session.getAttribute(UserController.USER_KEY);
		
		
		// samo administrator može da vidi druge korisnike; svaki korisnik može da vidi sebe
		
		if (loggedInUser == null || (!loggedInUser.isAdministrator() && !loggedInUser.getUsername().equals(username))) {
			response.sendRedirect(baseURL + "Users");
			return null;
		}

	
		User user = userService.findOne(username);
		if (user == null) {
			response.sendRedirect(baseURL + "Users");
			return null;
		}

	
		ModelAndView rezultat = new ModelAndView("user");
		rezultat.addObject("user", user);

		return rezultat;
	}
	
	
	
	
	// ---------------------------- EDIT USER 
	
	
	@PostMapping(value="/Edit")
	public void Edit(@RequestParam String username, @RequestParam Boolean administrator, @RequestParam Boolean block,
					  HttpSession session, HttpServletResponse response) throws IOException {
		
		
		
		
		User loggedInUser = (User) session.getAttribute(UserController.USER_KEY);
		if (loggedInUser == null || !loggedInUser.isAdministrator()) {
			response.sendRedirect(baseURL + "Users");
			return;
		}

		
		
		User user = userService.findOne(username);
		if (user == null) {
			response.sendRedirect(baseURL + "Users");
			return;
		}
		
		user.setAdministrator(administrator);
		userService.update(user);
		
	
//		user.setBlock(block);
//		userService.update1(user);
		
		User user1 = userService.findOne1(username);
		if (user1 != null ) {
			
			
			user1.setBlock(block);
			userService.update1(user1);
			
			
			response.sendRedirect(baseURL + "Users");
			return;
			
		}
		
		
		
		
		System.out.println("bbbb");
		
		
		
		response.sendRedirect(baseURL + "Users");
	}
	
	
	@GetMapping(value="/Profile")
	public ModelAndView profile(@RequestParam String username, 
			HttpSession session, HttpServletResponse response) throws IOException {		
		
		User loggedInUser = (User) session.getAttribute(UserController.USER_KEY);
		
		
		if (loggedInUser == null || (!loggedInUser.getUsername().equals(username))) {
			response.sendRedirect(baseURL);
			return null;
		}

	
		User user = userService.findOne(username);
		if (user == null) {
			response.sendRedirect(baseURL);
			return null;
		}

	
		ModelAndView rezultat = new ModelAndView("profile");
		rezultat.addObject("user", user);

		return rezultat;
	}
	
	
	

	
	// ---------------------------- REGISTRACIJA 
	
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
	
	
	// ---------------------------- LOGIN
	
	@PostMapping(value="/Login")
	public ModelAndView postLogin(@RequestParam String username, @RequestParam String password, 
			HttpSession session, HttpServletResponse response) throws IOException {
		try {
			
			/* Ako je User vec ulogovan */
			User loggedInUser = (User) session.getAttribute(UserController.USER_KEY);  
			if (loggedInUser != null) { 
			
				throw new Exception("You have to Log Out!");
			 }
		
			
			/* Pronalazi Usera */
			User user = userService.findOne(username, password);
			if (user == null) {
				throw new Exception("Invalid username or password!");
			}	
			
			
			/* BLOKIRAN Korisnik */
			User user1 = userService.findOne3(username, password);
			if (user1 == null) {
				throw new Exception("Your account is blocked!");
			}	
			
			
		

			session.setAttribute(UserController.USER_KEY, user);
			response.sendRedirect(baseURL);
			
			return null;
			
		} catch (Exception ex) {
			
			String poruka = ex.getMessage();
			if (poruka == "") {
				poruka = "Neuspešna prijava!";
			}
			
			
			/* Prosledjivanje */
			ModelAndView rezultat = new ModelAndView("index.html");
			rezultat.addObject("poruka", poruka);

			return rezultat;
		}
	}

	
	// ---------------------------- LOG OUT
	
	@GetMapping(value="/Logout")
	public void logout( HttpServletResponse response,HttpServletRequest request) throws IOException {
			
		
//		session.invalidate();
		
		request.getSession(true).removeAttribute("loggedInUser");
		
//		request.getSession(true).invalidate();
		
		response.sendRedirect(baseURL);
		
	}
	

}
