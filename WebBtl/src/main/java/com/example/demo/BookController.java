package com.example.demo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class BookController {
	@GetMapping("/")
	public String home(Model model) throws IOException{
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		List<Book> books = new ArrayList<Book>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/book", "root", "long2407");
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from book");
			while (resultSet.next()) {
				int bookcode = resultSet.getInt("bookcode");
				String title = resultSet.getString("title");
				String author = resultSet.getString("author");
				String description = resultSet.getString("description");
				String publication = resultSet.getString("publication");
				int numberpage = resultSet.getInt("numberpage");
				String category = resultSet.getString("category");
				String picturebook = resultSet.getString("picturebook");
				books.add(new Book(bookcode, title, author, description, publication, numberpage, category, picturebook));
			}
		} // End of try block
		catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("books", books);
		return "home";
	}
	
	@GetMapping("/books")
	public String getBooks(Model model) throws IOException {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		List<Book> books = new ArrayList<Book>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/book", "root", "long2407");
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from book");
			while (resultSet.next()) {
				int bookcode = resultSet.getInt("bookcode");
				String title = resultSet.getString("title");
				String author = resultSet.getString("author");
				String description = resultSet.getString("description");
				String publication = resultSet.getString("publication");
				int numberpage = resultSet.getInt("numberpage");
				String category = resultSet.getString("category");
				String picturebook = resultSet.getString("picturebook");
				books.add(new Book(bookcode, title, author, description, publication, numberpage, category, picturebook));
			}
		} // End of try block
		catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("books", books);
		return "books";
	}
	
	@GetMapping("/book/{bookcode}")
	public String getBook(Model model, @PathVariable String bookcode) {
		model.addAttribute("bookcode", bookcode);
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet result = null;

		Book book = new Book();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/book", "root", "long2407");
			ps = connection.prepareStatement("select * from book where bookcode = ?");
			ps.setInt(1, Integer.valueOf(bookcode));
			result = ps.executeQuery();
			while (result.next()) {
				book.setBookcode(result.getInt("bookcode"));
				book.setTitle(result.getString("title"));
				book.setAuthor(result.getString("author"));
				book.setDescription(result.getString("description"));
				book.setPublication(result.getString("publication"));
				book.setNumberpage(result.getInt("numberpage"));
				book.setCategory(result.getString("category"));
				book.setPicturebook(result.getString("picturebook"));
			}
		} // End of try block
		catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("book", book);
		return "book_detail";
	}
	
	@PostMapping("/book/save/{bookcode}")
	public String addBook(Book book, @PathVariable String bookcode) {
		Connection connection = null;
		PreparedStatement ps = null;
		int result = 0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/book", "root", "long2407");
			ps = connection.prepareStatement("INSERT INTO book VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setInt(1, Integer.valueOf(book.getBookcode()));
			ps.setString(2, book.getTitle());
			ps.setString(3, book.getAuthor());
			ps.setString(4, book.getDescription());
			ps.setString(5, book.getPublication());
			ps.setInt(6, Integer.valueOf(book.getNumberpage()));
			ps.setString(7, book.getCategory());
			ps.setString(8, book.getPicturebook());
			result = ps.executeUpdate();
			ps.close();
			connection.close();
			// Redirect the response to success page
			return "redirect:/books";
		} // End of try block
		catch (Exception e) {
			e.printStackTrace();
		}
		return "error"; // tạo trang Error
	}
	@PutMapping("/book/save/{bookcode}")
	public String updateBook(Book book, @PathVariable String bookcode) {
		Connection connection = null;
		PreparedStatement ps = null;
		int result = 0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/book", "root", "long2407");
			ps = connection.prepareStatement("UPDATE book SET title=?,author=?,description=?,publication=?,numberpage=?,category=?,picturebook=? WHERE bookcode=?");
			ps.setString(1, book.getTitle());
			ps.setString(2, book.getAuthor());
			ps.setString(3, book.getDescription());
			ps.setString(4, book.getPublication());
			ps.setInt(5, Integer.valueOf(book.getNumberpage()));
			ps.setString(6, book.getCategory());
			ps.setString(7, book.getPicturebook());
			ps.setInt(8, Integer.valueOf(bookcode));
			result = ps.executeUpdate();
			ps.close();
			connection.close();
			// Redirect the response to success page
			return "redirect:/books";
		} // End of try block
		catch (Exception e) {
			e.printStackTrace();
		}
		return "error"; // tạo trang Error
	}
	@DeleteMapping("/book/delete/{bookcode}")
	public String deleteBook(Book book, @PathVariable String bookcode) {
		Connection connection = null;
		PreparedStatement ps = null;
		int result = 0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/book", "root", "long2407");
			ps = connection.prepareStatement("DELETE FROM book WHERE bookcode=?");
			ps.setInt(1, Integer.valueOf(book.getBookcode()));
			result = ps.executeUpdate();
			ps.close();
			connection.close();
			// Redirect the response to success page
			return "redirect:/books";
		} // End of try block
		catch (Exception e) {
			e.printStackTrace();
		}
		return "error"; // tạo trang Error
	}
}
