package com.ftn.ProjekatOWP.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ftn.ProjekatOWP.dao.BookDAO;
import com.ftn.ProjekatOWP.dao.CommentDAO;
import com.ftn.ProjekatOWP.dao.ShoppingCartDAO;
import com.ftn.ProjekatOWP.dao.UserDAO;

import com.ftn.ProjekatOWP.model.Book;
import com.ftn.ProjekatOWP.model.Comment;
import com.ftn.ProjekatOWP.model.ShoppingCart;
import com.ftn.ProjekatOWP.model.User;

@Repository
public class CommentDAOImpl  implements CommentDAO{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private BookDAO bookDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	private class CommentRowMapper implements RowMapper<Comment> {

		@Override
		public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			int index = 1;
			
			Long id = rs.getLong(index++);
			
			String rating = rs.getString(index++);
			String text = rs.getString(index++);
			
			Date dateComment = rs.getTimestamp(index++);
			Boolean statusComment = rs.getBoolean(index++);
			
			Long bookId = rs.getLong(index++);
			Book bookComment = bookDAO.findOne(bookId);
			
			String username = rs.getString(index++);
			User userComment = userDAO.findOne(username);
			
			Comment comment = new Comment(id, rating, text, dateComment, statusComment, bookComment, userComment);
			return comment;
			
		}

	}
	
	@Override
	public Comment findOne(Long id) {
		String sql = 
				"SELECT c.id, c.rating, c.text, c.dateComment, c.statusComment, c.bookComment, c.userComment  FROM comments c " 
						+"LEFT JOIN books b ON b.id = c.bookComment "
						+"LEFT JOIN users u ON u.username = c.userComment "
						+"WHERE c.id = ? " 
						+" ORDER BY c.id";
		
		return jdbcTemplate.queryForObject(sql, new CommentRowMapper(), id);
		
		
	}
	
	
	@Override
	public List<Comment> findAll(Long bookId) {
		String sql = 
				"SELECT c.id, c.rating, c.text, c.dateComment, c.statusComment, c.bookComment, c.userComment  FROM comments c " 
						+"LEFT JOIN books b ON b.id = c.bookComment "
						+"LEFT JOIN users u ON u.username = c.userComment "
						+"WHERE  c.bookComment = ?  AND statusComment != false  " 
						+" ORDER BY c.id";
		return jdbcTemplate.query(sql, new CommentRowMapper(), bookId);
	}
	
	@Override
	public List<Comment> findAll() {
		String sql = 
				"SELECT c.id, c.rating, c.text, c.dateComment, c.statusComment, c.bookComment, c.userComment  FROM comments c " 
						+"LEFT JOIN books b ON b.id = c.bookComment "
						+"LEFT JOIN users u ON u.username = c.userComment "
						+" ORDER BY c.id";
		return jdbcTemplate.query(sql, new CommentRowMapper());
	}
	
	
	@Override
	public int save(Comment comment) {
		
		
		String sql = "INSERT INTO comments (rating, text, dateComment, statusComment, bookComment, userComment) VALUES (?, ?, ?, ?, ?, ?) ";
		return jdbcTemplate.update(sql, comment.getRating(), comment.getText(), comment.getDateComment(), comment.isStatusComment(),
																					comment.getBookComment().getId(), comment.getUserComment().getUsername());
	}
	
	@Override
	public int update(Comment comment) {
		String sql = "UPDATE comments  SET statusComment = ? "
				+ " WHERE id  = ?";
		return jdbcTemplate.update(sql, comment.isStatusComment(), comment.getId());
	}
	

}