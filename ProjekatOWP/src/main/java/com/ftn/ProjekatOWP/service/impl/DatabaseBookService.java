package com.ftn.ProjekatOWP.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.ProjekatOWP.dao.BookDAO;
import com.ftn.ProjekatOWP.model.Book;
import com.ftn.ProjekatOWP.model.Genre;
import com.ftn.ProjekatOWP.service.BookService;
import com.mysql.cj.jdbc.Blob;

@Service
public class DatabaseBookService implements BookService {
	

	@Autowired
	private BookDAO bookDAO;
	
	@Override
	public Book findOne(Long id) {
		return bookDAO.findOne(id);
	}
	
//	@Override
//	public Book findOne1(String ISBN) {
//		return bookDAO.findOne1(ISBN);
//	}
	

	@Override
	public List<Book> findAll() {
		return bookDAO.findAll();
	}
	
	
	@Override
	public List<Book> find(String ISBN, String name, String publishingHouse, String author, String yearOfPublication,
							String description, String image, Double price, Integer numberOfPages, String typeOfCover, String letter, Integer numberOfBooks) {
		
		
		return bookDAO.find(ISBN, name, publishingHouse, author, yearOfPublication, description,
							image, price, numberOfPages, typeOfCover, letter, numberOfBooks);
	}
	

	
	@Override
	public Book save(Book book) {
		bookDAO.save(book);
		return book;
	}
	
	@Override
	public Book update(Book book) {
		bookDAO.update(book);
		return book;
	}
	


	
}