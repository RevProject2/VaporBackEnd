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

import com.revature.beans.Carts;
import com.revature.beans.Libraries;
import com.revature.beans.Purchases;
import com.revature.beans.Users;
import com.revature.services.CartService;
import com.revature.services.LibraryService;
import com.revature.services.UserService;

@RestController
@RequestMapping(path="/users")
@CrossOrigin(allowCredentials = "true", origins = {"http://localhost:4200","http://revproject2frontend.s3-website-us-west-2.amazonaws.com/"})
public class UserController {
	
	private UserService us;
	private LibraryService ls;
	private CartService cs;
	
	@Autowired
	public UserController(UserService us, LibraryService ls, CartService cs) {
		this.us = us;
		this.ls = ls;
		this.cs = cs;
	}
	
	@GetMapping
	public List<Users> getAllUsers(HttpServletRequest request) {
		//System.out.println(request.getSession().getId());
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
		Users update_user = us.getById(user.getId());
		
//		Integer id = (Integer)request.getSession().getAttribute("id");
//		System.out.println(user);
//		System.out.println(id);
		if (update_user != null) {
			request.getSession().setAttribute("user", update_user);
			return ResponseEntity.ok(update_user);
		}
		return ResponseEntity.status(401).build();
	}
	
	@PostMapping(path="/purchase/{id}")
	public ResponseEntity<Integer> purchaseCart(@PathVariable Integer id, HttpServletRequest request) {
		//Integer id = (Integer)request.getSession().getAttribute("id");
		boolean b = us.purchaseCart(id);
		if (b == true) {
			return new ResponseEntity<>(id, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(id, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping(path="/deposit/{id}")
	public ResponseEntity<Users> deposit(@RequestBody Users u, @PathVariable Integer id, HttpServletRequest request) {
		Users user = us.getById(id);
		boolean check = us.deposit(user, u.getBalance());
		if (check) {
			request.getSession().setAttribute("user", user);
			return new ResponseEntity<>(u, HttpStatus.OK);
		}
		return new ResponseEntity<>(u, HttpStatus.BAD_REQUEST);
		
	}
	
	@GetMapping(path="/history/{id}")
	public ResponseEntity<List<Purchases>> getHistory(@PathVariable Integer id, HttpServletRequest request) {
		//Integer id = (Integer)request.getSession().getAttribute("id");
		List<Purchases> history = us.getHistory(id);
		if (history != null) {
			return new ResponseEntity<>(history, HttpStatus.OK);
		}
		return new ResponseEntity<>(history, HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping(path="/libraries/{id}")
	public ResponseEntity<List<Libraries>> getLibraryById(@PathVariable Integer id, HttpServletRequest request) {
		Users u = us.getById(id);
		if (u != null) {
			List<Libraries> l = ls.get(u.getId());
			return new ResponseEntity<>(l, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(path="/carts/{id}")
	public ResponseEntity<List<Carts>> getCartsById(@PathVariable Integer id) {
		Users u = us.getById(id);
		if (u != null) {
			List<Carts> c = cs.get(u.getId());
			return new ResponseEntity<>(c, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
}
