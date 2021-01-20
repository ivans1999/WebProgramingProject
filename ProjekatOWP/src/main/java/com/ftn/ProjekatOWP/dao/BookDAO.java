package com.ftn.ProjekatOWP.dao;

import java.util.List;
import com.ftn.ProjekatOWP.model.Book;


public interface BookDAO {

	public Book findOne(Long id);
	

	public List<Book> findAll();
	
	public List<Book> find(String ISBN, String name, String publishingHouse, String author, String yearOfPublication,
								String description, byte[] image, Double price, Integer numberOfPages, String typeOfCover, String letter, Integer numberOfBooks);
	

	
	public void save(Book book);
	
	public int update(Book book);
	
	
	
}
