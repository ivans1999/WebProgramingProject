package com.ftn.ProjekatOWP.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ftn.ProjekatOWP.model.Book;
import com.ftn.ProjekatOWP.model.ShoppingCart;
import com.ftn.ProjekatOWP.model.User;
import com.ftn.ProjekatOWP.service.BookService;
import com.ftn.ProjekatOWP.service.ShoppingCartService;

@Controller
@RequestMapping(value="/WishList")
public class WishListController {
	
	public static final String CHOOSEN_BOOKS_FOR_USER = "choosenBooksForUser";
	
	private  String bURL; 
	
	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	

	/** inicijalizacija podataka za kontroler */
	
	@PostConstruct
	public void init() {	
		
		//Specify the base URL for all relative URLs in a document
		bURL = servletContext.getContextPath()+"/";			
	}
	
	
	
	
	@GetMapping
	public ModelAndView get(
			@RequestParam String username, 
			HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		
		
		User user = (User) request.getSession().getAttribute(UserController.USER_KEY);
		
		if(username == null || user.isAdministrator() == true) {
			response.sendRedirect(bURL+"books");
		}
		
		User loggedInUser = (User) session.getAttribute(UserController.USER_KEY);
		
		if (loggedInUser == null || (!loggedInUser.getUsername().equals(username))) {
			response.sendRedirect(bURL+"books");
			return null;
		}
		
		
		List<ShoppingCart> shoppingCart = shoppingCartService.findAll(username);
		
		
		// prosleđivanje
		
		ModelAndView rezultat = new ModelAndView("wishList");
		rezultat.addObject("shoppingCart", shoppingCart);
		
		
		
		return rezultat;
	}
	
	
	
	
	@PostMapping
	public ModelAndView addToWishList(
			
			@RequestParam String bookId,
			@RequestParam String username,
			
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
	
		
		User user = (User) request.getSession().getAttribute(UserController.USER_KEY);		
		if(user==null || user.isAdministrator()==true) {
			response.sendRedirect(bURL+"books");
		}
		
		Long id = new Long(bookId);
		
		if(bookId!=null && id<=0) {
			response.sendRedirect(bURL+"books");
			return null;
		}
		
		Book book = bookService.findOne(id);
		if(book==null) {
			response.sendRedirect(bURL+"books");
			return null;
		}
		

		
		
		// TREBA PROVERA DA LI VEC POSTOJI KNJIGA U LISTI ZELJA - AKO HOCU ISTU DA DODAM PONOVO ???
		
		//System.out.println(id);
		System.out.println(username);
		
		
		List<ShoppingCart> shoppingCart1 = shoppingCartService.findOne(username, id);
		
		if(shoppingCart1!=null) {
			
			System.out.println("asasdasdasdasdas");
			
//			throw new Exception("Its samee!");
			
			// napraviti da ne moze da doda istu knjigu u listu zelja
			
		}
		
		
		
		
		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxx");	
		
		
		ShoppingCart shoppingCart = new ShoppingCart(book, user);
		shoppingCartService.save(shoppingCart);
		
		
		
		
		
		List<ShoppingCart> shoppingCart2 = shoppingCartService.findAll(username);

		// prosleđivanje
		
		ModelAndView rezultat = new ModelAndView("wishList");
		rezultat.addObject("shoppingCart", shoppingCart2);

		return rezultat;
	}
	
	
	
	// smisliti za domaci kako da kupi n karata gde se taj broj prosledi od strane korisnika
	// i voditi racuna koliko koja projekcija ima karata
			
	
	@PostMapping(value="/Remove")
	public ModelAndView remove(@RequestParam Long cartId, 	@RequestParam String username,
			HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
	
		// čitanje
	
			
		ShoppingCart shoppingCart1 = shoppingCartService.findOne(cartId);
		if(shoppingCart1==null) {
			
			System.out.println("asasdasdasdasdas");
			response.sendRedirect(bURL+"books");
			
			return null;
		}
		
		shoppingCartService.delete(cartId);
			
		
		System.out.println("aaaaaaaaaaaaaaaaaa");
		
	
		
		List<ShoppingCart> shoppingCart2 = shoppingCartService.findAll(username);

	
		
		ModelAndView rezultat = new ModelAndView("wishList");
		rezultat.addObject("shoppingCart", shoppingCart2);
		
		
		
		return rezultat;
	}
}
