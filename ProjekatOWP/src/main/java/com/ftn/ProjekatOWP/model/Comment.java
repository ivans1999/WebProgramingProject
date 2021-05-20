package com.ftn.ProjekatOWP.model;

import java.util.Date;

public class Comment {

	private Long id;
	
	private String rating;
	private String text;
	
	private Date dateComment;
	
	private boolean statusComment = false;
	
	private Book bookComment;
	private User userComment;
	
	
	public Comment(Long id, String rating, String text, Date dateComment, boolean statusComment, Book bookComment,
			User userComment) {
		super();
		this.id = id;
		this.rating = rating;
		this.text = text;
		this.dateComment = dateComment;
		this.statusComment = statusComment;
		this.bookComment = bookComment;
		this.userComment = userComment;
	}

	public Comment(String rating, String text, Date dateComment, boolean statusComment, Book bookComment,
			User userComment) {
		super();
		this.rating = rating;
		this.text = text;
		this.dateComment = dateComment;
		this.statusComment = statusComment;
		this.bookComment = bookComment;
		this.userComment = userComment;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getDateComment() {
		return dateComment;
	}

	public void setDateComment(Date dateComment) {
		this.dateComment = dateComment;
	}

	public boolean isStatusComment() {
		return statusComment;
	}

	public void setStatusComment(boolean statusComment) {
		this.statusComment = statusComment;
	}

	public Book getBookComment() {
		return bookComment;
	}

	public void setBookComment(Book bookComment) {
		this.bookComment = bookComment;
	}

	public User getUserComment() {
		return userComment;
	}

	public void setUserComment(User userComment) {
		this.userComment = userComment;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", rating=" + rating + ", text=" + text + ", dateComment=" + dateComment
				+ ", statusComment=" + statusComment + ", bookComment=" + bookComment + ", userComment=" + userComment
				+ "]";
	}
	
	
	
	
	
}
