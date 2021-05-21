package com.ftn.ProjekatOWP.service;

import java.util.List;

import com.ftn.ProjekatOWP.model.Book;
import com.ftn.ProjekatOWP.model.Genre;
//import com.mysql.cj.jdbc.Blob;

public interface BookService {
	
	Book findOne(Long id);
	
//	Book findOne1(String ISBN);
	
	List<Book> findAll();
	
	List<Book> find(String ISBN, String name, String publishingHouse, String author, String yearOfPublication,
						String description, String image, Double price, Integer numberOfPages, String typeOfCover, String letter,  Integer numberOfBooks);

	
	
	Book save(Book book);
	
	Book update(Book book);
	
	
	
}