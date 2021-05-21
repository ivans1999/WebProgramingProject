package com.ftn.ProjekatOWP.controller;

import java.io.IOException;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.LocaleResolver;

@Controller
@RequestMapping(value="/Internationalization")
public class InternationalizationController implements ServletContextAware {
	
	@Autowired
	private ServletContext servletContext;
	private String baseURL;

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	
	@PostConstruct
	public void init() {
		baseURL = servletContext.getContextPath() + "/";
	}
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private LocaleResolver localeResolver;
	

	// Internacionalizacijaaa
	
	@GetMapping("/changeToSerbian")
	public void change(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		System.out.println("Postavljanje lokalizacije za sesiju iz kontrolera na SERBIAN");
		
		localeResolver.setLocale(request, response, Locale.forLanguageTag("sr"));
		
		response.sendRedirect(baseURL);
	}
	
	@GetMapping("/changeToEngland")
	public void change2(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		System.out.println("Postavljanje lokalizacije za sesiju iz kontrolera na ENGLAND");
		
		localeResolver.setLocale(request, response, Locale.forLanguageTag("en"));
		
		response.sendRedirect(baseURL);
	}	
}
