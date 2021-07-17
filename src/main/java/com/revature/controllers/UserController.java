package com.revature.controllers;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.Users;
import com.revature.services.UserService;

@RestController
@RequestMapping(path="/users")
@CrossOrigin(allowCredentials = "true", origins = {"http://localhost:4200","http://revproject2frontend.s3-website-us-west-2.amazonaws.com/"})
public class UserController {
	
	private UserService us;
	
	@Autowired
	public UserController(UserService us) {
		this.us = us;
	}
	
	@GetMapping
	public List<Users> getAllUsers(HttpServletRequest request) {
		System.out.println(request.getSession().getId());
		return us.getAll();
	}
	
	@GetMapping(path="/{id}")
	public ResponseEntity<Users> getUserById(@PathVariable("id") Integer id) {
		Users u = us.getById(id);
		return ResponseEntity.ok(u);
	}
	
	
	
	@PostMapping
	public ResponseEntity<Users> addUser(@RequestBody Users u) {
		Users user = us.add(u);
		if (user != null) {
			return ResponseEntity.created(URI.create("http://localhost:8080/users" + user.getId())).build();
		}
		return ResponseEntity.status(409).build();
	}
	
	@PutMapping
	public ResponseEntity<Users> updateUser(@RequestBody Users u) {
		Users user = us.update(u);
		return ResponseEntity.created(URI.create("http://localhost:8080/users" + user.getId())).build();
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Integer> deleteUserById(@PathVariable("id") Integer id) {
		// Currently we won't have deleting account functionality
		// So this method does not really matter
		// In the future, should add some error handling based on delete
		us.deleteById(id);
		return new ResponseEntity<>(id, HttpStatus.OK);
	}
	
	@PostMapping(path="/login")
	public ResponseEntity<Users> login(@RequestBody Users u, HttpServletRequest request) {
		//System.out.println(request.getSession().getId());
		Users user = us.login(u.getUsername(), u.getPassword());
		if (user != null) {
			request.getSession().setAttribute("user", user);
			request.getSession().setAttribute("id", user.getId());
			return ResponseEntity.ok(user);
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping(path="/logout")
	public void logout(HttpServletRequest request) {
		//System.out.println(request.getSession().getId());
		request.getSession().invalidate();
	}
	
	@GetMapping(path="/account")
	public ResponseEntity<Users> getInfo(HttpServletRequest request) {
		//System.out.println(request.getSession().getId());
		Users user = (Users)request.getSession().getAttribute("user");
		Integer id = (Integer)request.getSession().getAttribute("id");
		System.out.println(user);
		System.out.println(id);
		if (user != null) {
			return ResponseEntity.ok(user);
		}
		return ResponseEntity.status(401).build();
	}
	
}
