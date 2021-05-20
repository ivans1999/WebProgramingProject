package com.ftn.ProjekatOWP.service;


import java.util.List;

import com.ftn.ProjekatOWP.model.ShoppingCart;

public interface ShoppingCartService {
	
	ShoppingCart findOne(Long cartId);
	
	List<ShoppingCart> findOne( String username, Long bookId);
	
	List<ShoppingCart> findAll();
	
	List<ShoppingCart> findAll(String username);
	
	List<ShoppingCart> findAll2(String bookId);
	
	ShoppingCart save(ShoppingCart shoppingCart);
	
	ShoppingCart delete(Long cartId);

}
