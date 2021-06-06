package com.ftn.ProjekatOWP.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import com.ftn.ProjekatOWP.model.Comment;
import com.ftn.ProjekatOWP.service.CommentService;

@Controller
@RequestMapping(value="/Comments")
public class CommentsController implements ServletContextAware{
	
	@Autowired
	private CommentService commentService;

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
	
	
	// ---------------------- ZA ADMINA - PRIKAZ
	
	
	@GetMapping(value="/ShowCommentsAdmin")
	public ModelAndView show() throws IOException {

	
		ModelAndView rezultat = new ModelAndView("commentsAdmin");
		return rezultat;
		
	}
	
	@GetMapping(value="/CommentsAdmin")
	@ResponseBody
	public Map<String, Object> commentsAdmin(
			HttpSession session, HttpServletResponse response) throws IOException {
		
		List<Comment> comments = commentService.findAll();

	
		Map<String, Object> odgovor = new LinkedHashMap<>();
		odgovor.put("status", "ok");
		odgovor.put("comments", comments);
		return odgovor;
	}
	
	
	
	
	// ---------------------  ZA ADMINA -- EDIT
	
	
	
	@GetMapping(value="/OneCommentAdmin")
	@ResponseBody
	@SuppressWarnings("unchecked")
	public ModelAndView oneComment() throws IOException {

	
		ModelAndView rezultat = new ModelAndView("oneCommentAdmin");
		return rezultat;
		
	}
	
	@GetMapping(value="/OneCommentAdminDetails")
	@ResponseBody
	@SuppressWarnings("unchecked")
	public Map<String, Object> oneCommentAdminDetails(@RequestParam Long id, 
			HttpSession session) throws IOException {
		
		Comment comment = commentService.findOne(id);


		Map<String, Object> odgovor = new LinkedHashMap<>();
		odgovor.put("status", "ok");
		odgovor.put("comment", comment);
		return odgovor;
	}
	
	
	@PostMapping(value="/EditOneCommentAdmin")
	@ResponseBody
	public Map<String, Object> editOneCommentAdmin(@RequestParam Long id, @RequestParam Boolean statusComment,
			
			HttpSession session, HttpServletRequest request) throws IOException {
		
		
		try {
		
			Comment comment = commentService.findOne(id);
			
			if (comment == null) {
				throw new Exception("Doesn't exist");
			}	
			if (statusComment.equals("")) {
				throw new Exception("Ne sme biti prazno polje");
			}
			

			comment.setStatusComment(statusComment);
			
			commentService.update(comment);
			
			Map<String, Object> odgovor = new LinkedHashMap<>();
			odgovor.put("status", "ok");	
			return odgovor;
			
		} catch (Exception ex) {
	
			String poruka = ex.getMessage();
			if (poruka == "") {
				poruka = "Failed";
			}
			
			Map<String, Object> odgovor = new LinkedHashMap<>();
			odgovor.put("status", "greska");
			odgovor.put("poruka", poruka);
			return odgovor;
		}
	}
	
	
}
