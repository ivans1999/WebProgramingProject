package com.ftn.ProjekatOWP.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.ProjekatOWP.dao.ShoppingCartDAO;
import com.ftn.ProjekatOWP.model.Book;
import com.ftn.ProjekatOWP.model.ShoppingCart;
import com.ftn.ProjekatOWP.service.ShoppingCartService;

@Service
public class DatabaseShoppingCartService implements ShoppingCartService{
	
	@Autowired
	private ShoppingCartDAO shoppingCartDAO;
	
	
	@Override
	public ShoppingCart findOne(Long cartId) {
		return shoppingCartDAO.findOne(cartId);
	}
	
	@Override
	public List<ShoppingCart> findOne( String username, Long bookId) {
		return shoppingCartDAO.findOne( username, bookId);
	}
	

	@Override
	public List<ShoppingCart> findAll() {
		return shoppingCartDAO.findAll();
	}
	
	@Override
	public List<ShoppingCart> findAll(String username) {
		return shoppingCartDAO.findAll(username);
	}
	
	@Override
	public List<ShoppingCart> findAll2(String bookId) {
		return shoppingCartDAO.findAll2(bookId);
	}
	
	@Override
	public ShoppingCart save(ShoppingCart shoppingCart) {
		shoppingCartDAO.save(shoppingCart);
		return shoppingCart;
	}

	@Override
	public ShoppingCart delete(Long cartId) {
		ShoppingCart shoppingCart = findOne(cartId);
		if (shoppingCart != null) {
			shoppingCartDAO.delete(cartId);
		}
		return shoppingCart;
	}

	
}
