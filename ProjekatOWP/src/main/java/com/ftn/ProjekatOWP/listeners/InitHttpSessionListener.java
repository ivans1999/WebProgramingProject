package com.ftn.ProjekatOWP.listeners;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.stereotype.Component;

import com.ftn.ProjekatOWP.controller.ShoppingCartController;
import com.ftn.ProjekatOWP.model.Book;

@Component
public class InitHttpSessionListener implements HttpSessionListener {

	
	/** Kreiranje sesije */
	
	public void sessionCreated(HttpSessionEvent event) {
		
		System.out.println("Inicijalizacija sesisje HttpSessionListener...");

		// pri kreiranju sesije inicijalizujemo je ili radimo neke dodatne aktivnosti	
		
		HttpSession session  = event.getSession();
		
		System.out.println("Session id korisnika je "+ session.getId());

		session.setAttribute(ShoppingCartController.CHOOSEN_BOOKS_FOR_USER_KEY, new ArrayList<Book>());

		System.out.println("Uspeh HttpSessionListener!");
	}
	
	
	
	/**  Brisanje sesije */
	
	public void sessionDestroyed(HttpSessionEvent arg0) {
		
		System.out.println("Brisanje SESIJE HttpSessionListener...");
		
		System.out.println("Uspesno HttpSessionListener!");
	}

}
