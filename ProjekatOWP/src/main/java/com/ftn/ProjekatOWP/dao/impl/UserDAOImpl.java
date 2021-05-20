package com.ftn.ProjekatOWP.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ftn.ProjekatOWP.dao.UserDAO;
import com.ftn.ProjekatOWP.model.Book;
import com.ftn.ProjekatOWP.model.User;

@Repository
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	private class UserRowMapper implements RowMapper<User> {

		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			

			
			int index = 1;
			String username = rs.getString(index++);
			String name = rs.getString(index++);
			String lastname = rs.getString(index++);
			String email = rs.getString(index++);
			String address = rs.getString(index++);
			String phoneNumber = rs.getString(index++);
			String gender = rs.getString(index++);
			LocalDateTime dateOfBirth = rs.getTimestamp(index++).toLocalDateTime();
			Date registrationDate = rs.getTimestamp(index++);
			Boolean administrator = rs.getBoolean(index++);
			Boolean block = rs.getBoolean(index++);
			

			User user = new User( name, lastname, username,null, email, address, phoneNumber, gender, dateOfBirth, registrationDate, administrator, block);
			return user;
			
			
		}
 
	}
	
	
	@Override
	public User findOne(String username) {
		try {
			String sql = "SELECT username, name, lastname, email, address, phoneNumber, gender, dateOfBirth, registrationDate, administrator, block"
						+ " FROM users WHERE username = ? ";
			return jdbcTemplate.queryForObject(sql, new UserRowMapper(), username);
		} catch (EmptyResultDataAccessException ex) {
	
			return null;
		}
	}
	
	@Override
	public User findOne1(String username) {
		try {
			String sql = "SELECT username, name, lastname, email, address, phoneNumber, gender, dateOfBirth, registrationDate, administrator, block"
						+ " FROM users WHERE username = ? and administrator != true ";
			return jdbcTemplate.queryForObject(sql, new UserRowMapper(), username);
		} catch (EmptyResultDataAccessException ex) {
	
			return null;
		}
	}
	
	
	@Override
	public User findOne2(String username) {
		try {
			String sql = "SELECT username, name, lastname, email, address, phoneNumber, gender, dateOfBirth, registrationDate, administrator, block"
						+ " FROM users WHERE username = ? and block = false ";
			return jdbcTemplate.queryForObject(sql, new UserRowMapper(), username);
		} catch (EmptyResultDataAccessException ex) {
	
			return null;
		}
	}
	
	
	@Override
	public User findOne(String username, String password) {
		try {
			String sql = "SELECT  username, name, lastname, email, address, phoneNumber, gender, dateOfBirth, registrationDate, administrator, block"
						+ " FROM users WHERE username = ? AND password = ?";
			return jdbcTemplate.queryForObject(sql, new UserRowMapper(), username, password);
		} catch (EmptyResultDataAccessException ex) {
		
			return null;
		}
	}
	
	
	
	@Override
	public User findOne3(String username, String password) {
		try {
			String sql = "SELECT  username, name, lastname, email, address, phoneNumber, gender, dateOfBirth, registrationDate, administrator, block"
						+ " FROM users WHERE username = ? AND password = ? AND block != true";
			return jdbcTemplate.queryForObject(sql, new UserRowMapper(), username, password);
		} catch (EmptyResultDataAccessException ex) {
		
			return null;
		}
	}
	
	
	
	@Override
	public void save(User user) {
		String sql = "INSERT INTO users (name, lastname, username, password, email, address, dateOfBirth, gender, phoneNumber, registrationDate, administrator, block) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, user.getName(), user.getLastname(), user.getUsername(), user.getPassword(), user.getEmail(), user.getAddress(),
				 user.getDateOfBirth(), user.getGender(),user.getPhoneNumber(), user.getRegistrationDate(),  user.isAdministrator(), user.isBlock());
	}
	
	
	
	  @Override public List<User> find(String username,String name,String
	  lastname,String email,String address,String phoneNumber, String gender,
	  LocalDateTime dateOfBirth, Date registrationDate, Boolean administrator, Boolean block) {
	  
	  ArrayList<Object> listaArgumenata = new ArrayList<Object>();
	  
	  String sql =
	  "SELECT username, name, lastname, email, address, phoneNumber, gender, dateOfBirth, registrationDate, administrator, block FROM users "
	  ;
	  
	  StringBuffer whereSql = new StringBuffer(" WHERE "); boolean imaArgumenata =
	  false;
	  
//	  if(username!=null) { username = "%" + username + "%"; if(imaArgumenata)
//	  whereSql.append(" AND "); whereSql.append("username LIKE ?"); imaArgumenata =
//	  true; listaArgumenata.add(username); }
//	  
//	  if(name!=null) { name = "%" + name + "%"; if(imaArgumenata)
//	  whereSql.append(" AND "); whereSql.append("name LIKE ?"); imaArgumenata =
//	  true; listaArgumenata.add(name); }
//	  
//	  if(lastname!=null) { lastname = "%" + lastname + "%"; if(imaArgumenata)
//	  whereSql.append(" AND "); whereSql.append("lastname LIKE ?"); imaArgumenata =
//	  true; listaArgumenata.add(lastname); }
//	  
//	  if(email!=null) { email = "%" + email + "%"; if(imaArgumenata)
//	  whereSql.append(" AND "); whereSql.append("email LIKE ?"); imaArgumenata =
//	  true; listaArgumenata.add(email); }
//	 
//	  if(address!=null) { address = "%" + address + "%"; if(imaArgumenata)
//	  whereSql.append(" AND "); whereSql.append("address LIKE ?"); imaArgumenata =
//	  true; listaArgumenata.add(address); }
//	  
//	  if(phoneNumber!=null) { phoneNumber = "%" + phoneNumber + "%";
//	  if(imaArgumenata) whereSql.append(" AND ");
//	  whereSql.append("phoneNumber LIKE ?"); imaArgumenata = true;
//	  listaArgumenata.add(phoneNumber); }
//	  
//	  if(gender!=null) { gender = "%" + gender + "%"; if(imaArgumenata)
//	  whereSql.append(" AND "); whereSql.append("gender LIKE ?"); imaArgumenata =
//	 true; listaArgumenata.add(gender); }
//	  
//	  if(dateOfBirth!=null) { if(imaArgumenata) whereSql.append(" AND ");
//	  whereSql.append("dateOfBirth LIKE ?"); imaArgumenata = true;
//	  listaArgumenata.add(dateOfBirth); }
//	  
//	  if(administrator!=null) { String administratorSql = (administrator)?
//	  "administrator = 1": "administrator >= 0"; if(imaArgumenata)
//	  whereSql.append(" AND "); whereSql.append(administratorSql); imaArgumenata =
//	  true; }
//	  
//	  if(registrationDate!=null) { if(imaArgumenata) whereSql.append(" AND ");
//	  whereSql.append("registrationDate LIKE ?"); imaArgumenata = true;
//	  listaArgumenata.add(registrationDate); }
//	  
	  
	  
	  
	  if(imaArgumenata) 
		  
		  sql=sql + whereSql.toString()+" ORDER BY username"; 
	  
	  else
		  
		  sql=sql + " ORDER BY username"; System.out.println(sql);
	  
	  return jdbcTemplate.query(sql, listaArgumenata.toArray(), new UserRowMapper()); 
	  
	  }
	 
	
	
	  @Override
		public int update(User user) {
			String sql = "UPDATE users  SET  administrator = ?  "
					+ " WHERE username  = ? and block = false ";
			return jdbcTemplate.update(sql, user.isAdministrator(), user.getUsername());
		}
	  
	  @Override
		public int update1(User user) {
			String sql = "UPDATE users  SET  block = ?  "
					+ " WHERE username  = ? and administrator != true ";
			return jdbcTemplate.update(sql, user.isBlock(), user.getUsername());
		}
	


}

