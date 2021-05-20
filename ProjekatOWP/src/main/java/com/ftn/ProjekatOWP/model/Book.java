package com.ftn.ProjekatOWP.model;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

//import com.mysql.cj.jdbc.Blob;

public class Book {
	
	private Long id;
	private String ISBN;
	private String name;
	private String publishingHouse;
	private String author;
	private String yearOfPublication;
	private String description;
	private String image;
	private Double price;
	private Integer numberOfPages;
	private String typeOfCover;
	private String letter;
	private Integer numberOfBooks;
	
	
	private List<Genre> genres = new ArrayList<>();


	public Book() {}



	public Book(Long id, String iSBN, String name, String publishingHouse, String author, String yearOfPublication,
			String description, String image, Double price, Integer numberOfPages, String typeOfCover, String letter,Integer numberOfBooks) {
		super();
		this.id = id;
		ISBN = iSBN;
		this.name = name;
		this.publishingHouse = publishingHouse;
		this.author = author;
		this.yearOfPublication = yearOfPublication;
		this.description = description;
		this.image = image;
		this.price = price;
		this.numberOfPages = numberOfPages;
		this.typeOfCover = typeOfCover;
		this.letter = letter;
		this.numberOfBooks = numberOfBooks;
		
	}



	public Book(String iSBN, String name, String publishingHouse, String author, String yearOfPublication,
			String description, String image, Double price, Integer numberOfPages, String typeOfCover, String letter) {
		super();
		ISBN = iSBN;
		this.name = name;
		this.publishingHouse = publishingHouse;
		this.author = author;
		this.yearOfPublication = yearOfPublication;
		this.description = description;
		this.image = image;
		this.price = price;
		this.numberOfPages = numberOfPages;
		this.typeOfCover = typeOfCover;
		this.letter = letter;
		
	}





	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPublishingHouse() {
		return publishingHouse;
	}

	public void setPublishingHouse(String publishingHouse) {
		this.publishingHouse = publishingHouse;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getYearOfPublication() {
		return yearOfPublication;
	}

	public void setYearOfPublication(String yearOfPublication) {
		this.yearOfPublication = yearOfPublication;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(Integer numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	public String getTypeOfCover() {
		return typeOfCover;
	}

	public void setTypeOfCover(String typeOfCover) {
		this.typeOfCover = typeOfCover;
	}

	public String getLetter() {
		return letter;
	}

	public void setLetter(String letter) {
		this.letter = letter;
	}

	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres.clear();
		this.genres.addAll(genres);
	}

	public Integer getNumberOfBooks() {
		return numberOfBooks;
	}


	public void setNumberOfBooks(Integer numberOfBooks) {
		this.numberOfBooks = numberOfBooks;
	}


	
	
	@Transient
	public String getImagePath() {
		if (image == null || id == null) 
			
			return null;
		
		return "images/" + image;
	}
	



	@Override
	public String toString() {
		return "Book [id=" + id + ", ISBN=" + ISBN + ", name=" + name + ", publishingHouse=" + publishingHouse
				+ ", author=" + author + ", yearOfPublication=" + yearOfPublication + ", description=" + description
				+ ", image=" + image + ", price=" + price + ", numberOfPages=" + numberOfPages
				+ ", typeOfCover=" + typeOfCover + ", letter=" + letter + ", numberOfBooks=" + numberOfBooks
				+ ", genres=" + genres + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime*result + ((id == null) ? 0 : id.hashCode());
		return 31 + id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
	
	
	
	
	
	
