package com.ftn.ProjekatOWP.dao;

import java.util.List;
import com.ftn.ProjekatOWP.model.Book;
//import com.mysql.cj.jdbc.Blob;

public interface BookDAO {

	public Book findOne(Long id);
	
//	public Book findOne1(String ISBN);

	public List<Book> findAll();
	
	public List<Book> find(String ISBN, String name, String publishingHouse, String author, String yearOfPublication,
								String description, String image, Double price, Integer numberOfPages, String typeOfCover, String letter, Integer numberOfBooks);
	

	
	public void save(Book book);
	
	public int update(Book book);
	
	
	
}
