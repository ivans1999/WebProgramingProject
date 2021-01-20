package com.ftn.ProjekatOWP.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import com.ftn.ProjekatOWP.model.Book;
import com.ftn.ProjekatOWP.model.User;
import com.ftn.ProjekatOWP.service.BookService;

@Controller
@RequestMapping(value="/books")
public class BooksController implements ServletContextAware {


	@Autowired
	private BookService bookService;

	
	@Autowired
	private ServletContext servletContext;
	private String baseURL;

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	
	@PostConstruct
	public void init() {
		baseURL = servletContext.getContextPath() + "/";
	}
	
	@GetMapping
	public ModelAndView index(
			@RequestParam(required=false) String ISBN,
			@RequestParam(required=false) String name,
			@RequestParam(required=false) String publishingHouse,
			@RequestParam(required=false) String author,
			@RequestParam(required=false) String yearOfPublication,
			@RequestParam(required=false) String description,
			@RequestParam(required=false) byte[] image,
			@RequestParam(required=false) Double price,
			@RequestParam(required=false) Integer numberOfPages,
			@RequestParam(required=false) String typeOfCover,
			@RequestParam(required=false) String letter,
			@RequestParam(required=false) Integer numberOfBooks,
			HttpSession session, HttpServletResponse response) throws IOException {		
		
		
	
		
		List<Book> books = bookService.find(ISBN, name, publishingHouse, author, yearOfPublication, description,
											image, price, numberOfPages, typeOfCover, letter, numberOfBooks);

		
		ModelAndView rezultat = new ModelAndView("books");
		rezultat.addObject("books", books);

		
		
		
		return rezultat;
	}
	
	@GetMapping(value="/Details")
	public ModelAndView details(@RequestParam Long id, 
			HttpSession session, HttpServletResponse response) throws IOException {
		
		Book book = bookService.findOne(id);
		if (book == null) {
			response.sendRedirect(baseURL + "books");
			return null;
		}
		

	
		ModelAndView rezultat = new ModelAndView("oneBook");
		rezultat.addObject("book", book);

		return rezultat;
	}
		
	
	@GetMapping(value="/Create")
	public String create(HttpSession session, HttpServletResponse response) throws IOException {
		
	
//		User loggedInUser = (User) session.getAttribute(UserController.USER_KEY);
//		if (loggedInUser == null || !loggedInUser.isAdministrator()) {
//			response.sendRedirect(baseURL + "books");
//			return null;
//		}

		return "addBook";
	}
	
	@PostMapping(value="/Create")
	public void create(@RequestParam String ISBN, @RequestParam String name, @RequestParam String publishingHouse, @RequestParam String author,
			@RequestParam String yearOfPublication, @RequestParam String description, @RequestParam byte[] image, @RequestParam Double price, 
			@RequestParam Integer numberOfPages, @RequestParam String typeOfCover, @RequestParam String letter, 
			HttpSession session, HttpServletResponse response) throws IOException {
		
	
		
		User loggedInUser = (User) session.getAttribute(UserController.USER_KEY);
		if (loggedInUser == null || !loggedInUser.isAdministrator()) {
			response.sendRedirect(baseURL + "books/Create");
			return;
		}
		    

		
		if ( ISBN.equals("")) {
			response.sendRedirect(baseURL + "books/Create");
			return;
		}
		
		if ( name.equals("")) {
			response.sendRedirect(baseURL + "books/Create");
			return;
		}
		
		if (publishingHouse.equals("")) {
			response.sendRedirect(baseURL + "books/Create");
			return;
		}
		
		if (author.equals("")) {
			response.sendRedirect(baseURL + "books/Create");
			return;
		}
		
		if (yearOfPublication.equals("")) {
			response.sendRedirect(baseURL + "books/Create");
			return;
		}
		
		if (description.equals("")) {
			response.sendRedirect(baseURL + "books/Create");
			return;
		}
		
		if (!typeOfCover.equals("Hardcover") && !typeOfCover.equals("Softcover")) {
			response.sendRedirect(baseURL + "books/Create");
			return;
		}
		
		if (!letter.equals("Latin") && !letter.equals("Cyrillic")) {
			response.sendRedirect(baseURL + "books/Create");
			return;
		}
		
		
		Book book = new Book(ISBN, name, publishingHouse, author, yearOfPublication, description, image, price, numberOfPages, typeOfCover, letter);
		bookService.save(book);

		response.sendRedirect(baseURL + "books");
		
	
	}
	
	@PostMapping(value="/Edit")
	public void Edit(
			@RequestParam Long id, @RequestParam byte[] image,
			@RequestParam String name, @RequestParam String publishingHouse, @RequestParam String author,
			@RequestParam Integer numberOfPages, @RequestParam String typeOfCover, @RequestParam String letter,
			@RequestParam double price, @RequestParam String description, @RequestParam String yearOfPublication,
			@RequestParam Integer numberOfBooks,
			HttpSession session, HttpServletResponse response) throws IOException {
		
		
		
		User loggedInUser = (User) session.getAttribute(UserController.USER_KEY);
		if (loggedInUser == null || !loggedInUser.isAdministrator()) {
			response.sendRedirect(baseURL + "books");
			return;
		}

	
		Book book = bookService.findOne(id);
		if (book == null) {
			response.sendRedirect(baseURL + "books");
			return;
		}
		
		
		
		
		book.setName(name);
		book.setPublishingHouse(publishingHouse);
		book.setAuthor(author);
		book.setNumberOfPages(numberOfPages);
		book.setTypeOfCover(typeOfCover);
		book.setYearOfPublication(yearOfPublication);
		book.setImage(image);
		book.setLetter(letter);
		book.setPrice(price);
		book.setDescription(description);
		book.setNumberOfBooks(numberOfBooks);
		
		bookService.update(book);
	
		response.sendRedirect(baseURL + "books");
	}

	
	
	

	
	
}