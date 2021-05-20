package com.ftn.ProjekatOWP.dao;

import java.util.List;

import com.ftn.ProjekatOWP.model.ShoppingCart;

public interface ShoppingCartDAO {
	
	public ShoppingCart findOne(Long cartId);
	
	public List<ShoppingCart> findOne(String username, Long bookId);
	
	public List<ShoppingCart> findAll();
	
	public List<ShoppingCart> findAll(String username);
	
	public List<ShoppingCart> findAll2(String bookId);
	
	public int save(ShoppingCart shoppingCart);
	
	public int delete(Long cartId);

	
	
}

