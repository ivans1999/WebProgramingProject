package com.ftn.ProjekatOWP.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ftn.ProjekatOWP.dao.BookDAO;
import com.ftn.ProjekatOWP.dao.ShoppingCartDAO;
import com.ftn.ProjekatOWP.dao.UserDAO;
import com.ftn.ProjekatOWP.model.Book;
import com.ftn.ProjekatOWP.model.ShoppingCart;
import com.ftn.ProjekatOWP.model.User;


@Repository
public class ShoppingCartDAOImpl implements ShoppingCartDAO{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private BookDAO bookDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	
	private class ShoppingCartRowMapper implements RowMapper<ShoppingCart> {

		@Override
		public ShoppingCart mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			int index = 1;
			
			Long cartId = rs.getLong(index++);
			
			Long bookId = rs.getLong(index++);
			
			Book book = bookDAO.findOne(bookId);
			
			String username = rs.getString(index++);

			User user = userDAO.findOne(username);
			
			ShoppingCart shoppingCart = new ShoppingCart(cartId, book, user);
			return shoppingCart;
			
		}

	}
	
	
	
	@Override
	public ShoppingCart findOne(Long cartId) {
		String sql = 
				"SELECT c.id, c.bookId, c.user  FROM carts c " 
						+"LEFT JOIN books b ON b.id = c.bookId "
						+"LEFT JOIN users u ON u.username = c.user "
						+"WHERE c.id = ? " 
						+" ORDER BY c.id";
		
		return jdbcTemplate.queryForObject(sql, new ShoppingCartRowMapper(), cartId);
		
		
	}
	
	
	@Override
	public List<ShoppingCart> findOne( String username, Long bookId) {
		String sql = 
				"SELECT c.id, b.id, u.username FROM carts c " 
						+"LEFT JOIN books b ON b.id = c.bookId "
						+"LEFT JOIN users u ON u.username = c.user "
						+"WHERE user = ? and bookId = ? " 
						+" ORDER BY c.id";
				
		return jdbcTemplate.query(sql, new ShoppingCartRowMapper(), username, bookId);
	}
	
	@Override
	public List<ShoppingCart> findAll() {
		String sql = 
				"SELECT c.id, b.id, u.username FROM carts c " 
				+"LEFT JOIN books b ON b.id = c.bookId "
				+"LEFT JOIN users u ON u.username = c.user "
				+" ORDER BY c.id";
		return jdbcTemplate.query(sql, new ShoppingCartRowMapper());
	}
	
	@Override
	public List<ShoppingCart> findAll(String username) {
		String sql = 
				"SELECT c.id, b.id, u.username FROM carts c " 
				+"LEFT JOIN books b ON b.id = c.bookId "
				+"LEFT JOIN users u ON u.username = c.user "
				+"WHERE user = ?  AND administrator != true   " 
				+" ORDER BY c.id";
		return jdbcTemplate.query(sql, new ShoppingCartRowMapper(), username);
	}
	
	
	
	@Override
	public List<ShoppingCart> findAll2(String bookId) {
		String sql = 
				"SELECT c.id, b.id, u.username FROM carts c " 
						+"LEFT JOIN books b ON b.id = c.bookId "
						+"LEFT JOIN users u ON u.username = c.user "
						+"WHERE c.bookId != ? " 
						+" ORDER BY c.id";
				
	
		
		return jdbcTemplate.query(sql, new ShoppingCartRowMapper(), bookId);
		
	
	}
	
	
	@Override
	public int save(ShoppingCart shoppingCart) {
		
		//INSERT INTO carts (bookId, user) VALUES (1, 'b');
		
		String sql = "INSERT INTO carts (bookId, user) VALUES (?, ?) ";
		return jdbcTemplate.update(sql, shoppingCart.getBook().getId(), shoppingCart.getUser().getUsername());
	}
	
	
	@Override
	public int delete(Long cartId) {
		String sql = "DELETE FROM carts WHERE id = ?";
		return jdbcTemplate.update(sql, cartId);
	}
	


}

