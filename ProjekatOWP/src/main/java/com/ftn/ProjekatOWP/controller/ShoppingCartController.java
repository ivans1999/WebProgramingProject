package com.ftn.ProjekatOWP.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

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
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import com.ftn.ProjekatOWP.model.Book;
import com.ftn.ProjekatOWP.service.BookService;

@Controller
@RequestMapping(value="/ShoppingCart")
public class ShoppingCartController {


	
	public static final String CHOOSEN_BOOKS_FOR_USER_KEY = "choosenBooksForUser";
	
	
	@Autowired
	private BookService bookService;
	
	

	@GetMapping
	public ModelAndView index(
			
			HttpSession session, HttpServletResponse response) throws IOException {		
		

			ModelAndView rezultat = new ModelAndView("shoppingCart");
			
			return rezultat;
		
	}

	
	
	
	@PostMapping(value="/Details")
	@SuppressWarnings("unchecked")
	public ModelAndView details(@RequestParam Long id, 
			HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		
		Book book = bookService.findOne(id);

	

		List<Book> bookSelected = (List<Book>) session.getAttribute(ShoppingCartController.CHOOSEN_BOOKS_FOR_USER_KEY);
		if (!bookSelected.contains(book)) {
			bookSelected.add(book);

		}
		
		
		
		ModelAndView rezultat = new ModelAndView("shoppingCart");
//		rezultat.addObject("book", book);
		
		
		return rezultat;
	}
	
	@PostMapping(value="/Buy")
	@SuppressWarnings("unchecked")
	public ModelAndView buy(
			HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		
	
		List<Book> bookSelected = (List<Book>) session.getAttribute(ShoppingCartController.CHOOSEN_BOOKS_FOR_USER_KEY);
		 session.setAttribute(ShoppingCartController.CHOOSEN_BOOKS_FOR_USER_KEY, bookSelected);
	
		
		ModelAndView rezultat = new ModelAndView("shoppingCart");

		
		
		return rezultat;
	}
	
	
	
	
	
	
	
	
	
	
	@PostMapping(value="/Remove")
	@SuppressWarnings("unchecked")
	public ModelAndView remove(@RequestParam Long id, 
			HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
	

		Book book = bookService.findOne(id);
		
		List<Book> bookSelected = (List<Book>) session.getAttribute(ShoppingCartController.CHOOSEN_BOOKS_FOR_USER_KEY);
		if(bookSelected.contains(book)) {
	        bookSelected.remove(book);     
	      }
		
	    			

		ModelAndView rezultat = new ModelAndView("shoppingCart");
		
		return rezultat;
	}
	
	

	
}