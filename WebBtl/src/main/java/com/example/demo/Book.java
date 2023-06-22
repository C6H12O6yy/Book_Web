package com.example.demo;

public class Book {
	private int bookcode;
    private String title, author, description, publication;;
    private int numberpage;
    private String category, picturebook;
	public Book() {
		
	}
	public Book(int bookcode, String title, String author, String description, String publication, int numberpage,
			String category, String picturebook) {
		super();
		this.bookcode = bookcode;
		this.title = title;
		this.author = author;
		this.description = description;
		this.publication = publication;
		this.numberpage = numberpage;
		this.category = category;
		this.picturebook = picturebook;
	}
	public int getBookcode() {
		return bookcode;
	}
	public void setBookcode(int bookcode) {
		this.bookcode = bookcode;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPublication() {
		return publication;
	}
	public void setPublication(String publication) {
		this.publication = publication;
	}
	public int getNumberpage() {
		return numberpage;
	}
	public void setNumberpage(int numberpage) {
		this.numberpage = numberpage;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getPicturebook() {
		return picturebook;
	}
	public void setPicturebook(String picturebook) {
		this.picturebook = picturebook;
	}
	
    
}
