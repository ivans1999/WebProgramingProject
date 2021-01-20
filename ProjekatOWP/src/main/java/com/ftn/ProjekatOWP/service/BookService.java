package com.ftn.ProjekatOWP.service;

import java.util.List;

import com.ftn.ProjekatOWP.model.Book;

public interface BookService {
	
	Book findOne(Long id);
	
	
	List<Book> findAll();
	
	List<Book> find(String ISBN, String name, String publishingHouse, String author, String yearOfPublication,
						String description, byte[] image, Double price, Integer numberOfPages, String typeOfCover, String letter,  Integer numberOfBooks);

	
	
	Book save(Book book);
	
	Book update(Book book);
	
	
	
}
