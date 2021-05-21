package com.ftn.ProjekatOWP.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ftn.ProjekatOWP.model.Book;
import com.ftn.ProjekatOWP.model.Comment;
import com.ftn.ProjekatOWP.model.Genre;
import com.ftn.ProjekatOWP.model.ShoppingCart;
import com.ftn.ProjekatOWP.model.User;
import com.ftn.ProjekatOWP.service.BookService;
import com.ftn.ProjekatOWP.service.CommentService;
//import com.ftn.ProjekatOWP.service.GenreService;
import com.ftn.ProjekatOWP.service.ShoppingCartService;
import com.ftn.ProjekatOWP.service.UserService;
//import com.mysql.cj.jdbc.Blob;

@Controller
@RequestMapping(value="/books")
public class BooksController implements ServletContextAware {


	@Autowired
	private BookService bookService;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ShoppingCartService shoppingCartService;

	
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
	
	private final String UPLOAD_DIR = "./images/";
	
	// ---------------------------- LISTA KNJIGA
	
	@GetMapping
	public ModelAndView index(
			@RequestParam(required=false) String ISBN,
			@RequestParam(required=false) String name,
			@RequestParam(required=false) String publishingHouse,
			@RequestParam(required=false) String author,
			@RequestParam(required=false) String yearOfPublication,
			@RequestParam(required=false) String description,
			@RequestParam(required=false) String image,
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
		
	
	
	// ---------------------------- ADD KNJIGE
	
	@GetMapping(value="/Create")
	public String create(HttpSession session, HttpServletResponse response) throws IOException {
		
	
		User loggedInUser = (User) session.getAttribute(UserController.USER_KEY);
		if (loggedInUser == null || !loggedInUser.isAdministrator()) {
			response.sendRedirect(baseURL + "books");
			return null;
		}

		
//		// čitanje
//		List<Genre> genres = genreService.findAll();

		
		// prosleđivanje
		
//		ModelAndView rezultat = new ModelAndView("addBook");
//		rezultat.addObject("zanrovi", zanrovi);

		return "addBook";
	}
	
	@PostMapping(value="/Create")
	public void create(@RequestParam String ISBN, @RequestParam String name, @RequestParam String publishingHouse, @RequestParam String author,
			@RequestParam String yearOfPublication, @RequestParam String description, @RequestParam Double price, 
			@RequestParam Integer numberOfPages, @RequestParam String typeOfCover, @RequestParam String letter, 
			
			@RequestParam("image") MultipartFile multipartFile,
			
			HttpSession session, HttpServletResponse response) throws IOException {
		
	

		
		
		User loggedInUser = (User) session.getAttribute(UserController.USER_KEY);
		if (loggedInUser == null || !loggedInUser.isAdministrator()) {
			response.sendRedirect(baseURL + "books");
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
		
		
		
		
		// -------------------------- UPISIVANJE *-* SLIKE ---
		
		// check if file is empty
        if (multipartFile.isEmpty()) {
        	throw new IOException("Could not save image file: ");
           
        }

        // normalize the file path
        
        String image = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        // save the file on the local file system
        
        try {
            Path path = Paths.get(UPLOAD_DIR + image);
            Files.copy(multipartFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
        	
            e.printStackTrace();
        }

		
		
		
		
		
		Book book = new Book(ISBN, name, publishingHouse, author, yearOfPublication, description, image, price, numberOfPages, typeOfCover, letter);
		bookService.save(book);

		response.sendRedirect(baseURL + "books");
		
	
	}
	
	
	
	
	// ---------------------------- EDIT KNJIGE
	
	@PostMapping(value="/Edit")
	public void Edit(
			@RequestParam Long id, @RequestParam String image,
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

	
	@GetMapping(value="/AllComments")
	@ResponseBody
	@SuppressWarnings("unchecked")
	public Map<String, Object> allComments(@RequestParam Long bookId, 
			HttpSession session) throws IOException {
	
		
		List<Comment> comments = commentService.findAll(bookId);


		Map<String, Object> odgovor = new LinkedHashMap<>();
		odgovor.put("status", "ok");
		odgovor.put("comments", comments);
		return odgovor;
	}
	
	
	@PostMapping(value="/AddComment")
	@ResponseBody
	public Map<String, Object> create(@RequestParam String text, @RequestParam String ratingComment, @RequestParam Long bookComment, @RequestParam String userComment,  
			HttpSession session, HttpServletRequest request) throws IOException {
	
		User loggedInUser = (User) session.getAttribute(UserController.USER_KEY);
		if (loggedInUser == null || loggedInUser.isAdministrator()) {
			
			Map<String, Object> odgovor = new LinkedHashMap<>();
			odgovor.put("status", "odbijen");
			return odgovor;
		}

		Book bookComment2 = bookService.findOne(bookComment);
		
		User userComment2 = userService.findOne(userComment);
		
		
		long yourmilliseconds = System.currentTimeMillis();
		
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm");    
	
		Date dateComment = new Date(yourmilliseconds);
		
		System.out.println(sdf.format(dateComment));
		
		
		try {
			

			if (text == null || text.equals("")) {
				throw new Exception("The text entry field must not be blank");
						
			}
			if (ratingComment == null || ratingComment.equals("")) {
				throw new Exception("The ratingComment entry field must not be blank");
				
			}


			
			boolean statusComment=false;
			
			Comment comment = new Comment(ratingComment, text, dateComment, statusComment=false, bookComment2, userComment2);
			commentService.save(comment);
			
			Map<String, Object> odgovor = new LinkedHashMap<>();
			odgovor.put("status", "ok");	
			return odgovor;
			
		} catch (Exception ex) {
	
			String poruka = ex.getMessage();
			if (poruka == "") {
				poruka = "Failed!";
			}
			
			Map<String, Object> odgovor = new LinkedHashMap<>();
			odgovor.put("status", "greska");
			odgovor.put("poruka", poruka);
			return odgovor;
		}
		
		
	}
	
	
	
	

	
	
}