package com.ftn.ProjekatOWP.dao.impl;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ftn.ProjekatOWP.dao.BookDAO;
import com.ftn.ProjekatOWP.model.Book;

@Repository
public class BookDAOImpl implements BookDAO{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	

	
	@Override
	public Book findOne(Long id) {
		String sql =
				"SELECT b.id, b.ISBN, b.name, b.publishingHouse, b.author, b.yearOfPublication, b.description , b.price, b.numberOfPages, b.typeOfCover, b.letter, b.numberOfBooks "
				+"FROM books b "
				+"WHERE b.id = ? "
				+"ORDER BY b.id";
		return jdbcTemplate.queryForObject(sql, new BookRowMapper(), id);
	}
	
	

	@Override
	public List<Book> find(String ISBN, String name, String publishingHouse, String author, String yearOfPublication, String description, byte[] image, Double price, Integer numberOfPages, String typeOfCover, String letter, Integer numberOfBooks) {
		
		ArrayList<Object> listaArgumenata = new ArrayList<Object>();
		
		String sql = "SELECT id, ISBN, name, publishingHouse, author, yearOfPublication, description , price, numberOfPages, typeOfCover, letter, numberOfBooks FROM books ";
		
		StringBuffer whereSql = new StringBuffer(" WHERE ");
		boolean imaArgumenata = false;
		
		if(name!=null) {
			name = "%" + name + "%";
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("name LIKE ?");
			imaArgumenata = true;
			listaArgumenata.add(name);
		}
		
		if(author!=null) {
			author = "%" + author + "%";
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("author LIKE ?");
			imaArgumenata = true;
			listaArgumenata.add(author);
		}
		
		
		if(price!=null) {
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("price = ?");
			imaArgumenata = true;
			listaArgumenata.add(price);
		}
		
		
		
		if(imaArgumenata)
			sql=sql + whereSql.toString()+" ORDER BY id";
		else
			sql=sql + " ORDER BY id";
		System.out.println(sql);
		
		return jdbcTemplate.query(sql, listaArgumenata.toArray(), new BookRowMapper());
		
		
	}
	
	

	
	@Override
	public List<Book> findAll() {
		String sql = "SELECT ISBN, name, publishingHouse, author, yearOfPublication, description, image, price, numberOfPages, typeOfCover, letter, numberOfBooks FROM books";
		return jdbcTemplate.query(sql, new BookRowMapper());
	}
	
	private class BookRowMapper implements RowMapper<Book> {

		@Override
		public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			int index = 1;
			Long id = rs.getLong(index++);
			String ISBN = rs.getString(index++);
			String name = rs.getString(index++);
			String publishingHouse = rs.getString(index++);
			String author = rs.getString(index++);
			String yearOfPublication = rs.getString(index++);
			String description = rs.getString(index++);
			
			byte[] image = rs.getBytes(1);
			
			Double price = rs.getDouble(index++);
			Integer numberOfPages = rs.getInt(index++);
			String typeOfCover = rs.getString(index++);
			String letter = rs.getString(index++);
			Integer numberOfBooks = rs.getInt(index++);
			
			
	
			Book book = new Book(id, ISBN, name, publishingHouse, author, yearOfPublication, description,
									image, price, numberOfPages, typeOfCover, letter, numberOfBooks);
			return book;
		}
	
	
	
	}
	
	@Override
	public void save(Book book) {
		String sql = "INSERT INTO books (ISBN, name, publishingHouse, author, yearOfPublication, description, image, price, numberOfPages, typeOfCover, letter) VALUES (?, ?, ?, ?, ? ,? ,? ,? ,? ,? , ? )";
		jdbcTemplate.update(sql, book.getISBN(), book.getName(), book.getPublishingHouse(), book.getAuthor(), 
				book.getYearOfPublication(), book.getDescription() , book.getImage(), book.getPrice() , book.getNumberOfPages(),
				book.getTypeOfCover(), book.getLetter());
	}
	
	@Override
	public int update(Book book) {
		String sql = "UPDATE books  SET ISBN = ?,  name = ?, publishingHouse = ?, author = ?, yearOfPublication = ?, description = ? , image = ?, price = ? , numberOfPages = ? , typeOfCover = ? , letter = ?, "
				+ "numberOfBooks = numberOfBooks + ?"
				+ " WHERE id  = ?";
		return jdbcTemplate.update(sql, book.getISBN(), book.getName(), book.getPublishingHouse(), book.getAuthor(), book.getYearOfPublication(), book.getDescription(), book.getImage(), book.getPrice(), 
										book.getNumberOfPages(), book.getTypeOfCover(), book.getLetter(), book.getNumberOfBooks(), book.getId());
	}
	
	
	
}


