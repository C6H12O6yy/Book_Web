package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
	@GetMapping("/login")
	public String loginForm() {
		return "login";
	}
	@GetMapping("/register")
	public String registerForm(){
		return "register";
	}
	@PostMapping("/login")
	public String login(User user, Model model){
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/book", "root", "long2407");
			ps = connection.prepareStatement("select * from user where email = ? ");
			ps.setString(1,user.getEmail());
			result = ps.executeQuery();
			while (result.next()) {
				if(user.getPassword().equalsIgnoreCase(result.getString("password"))) {
					return "redirect:/books";
				}
			}
		} // End of try block
		catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("message", "Invalid information");
		return "redirect:/login"; // tạo trang Error
	}
	
	@PostMapping("/register")
	public String register(User user){
		Connection connection = null;
		PreparedStatement ps = null;
		int result = 0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/book", "root", "long2407");
			ps = connection.prepareStatement("INSERT INTO user VALUES (?, ?, ?, ?, ?, ?)");
			ps.setInt(1, Integer.valueOf(user.getId()));
			ps.setString(2, user.getName());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getPassword());
			ps.setInt(5, Integer.valueOf(user.getPhone()));
			ps.setString(6, user.getLocal());
			result = ps.executeUpdate();
			ps.close();
			connection.close();
			// Redirect the response to success page
			return "redirect:/login";
		} // End of try block
		catch (Exception e) {
			e.printStackTrace();
		}
		return "error"; // tạo trang Error
	}
}
