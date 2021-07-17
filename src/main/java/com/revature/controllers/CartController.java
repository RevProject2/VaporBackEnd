package com.revature.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.Carts;
import com.revature.beans.Games;
import com.revature.beans.Users;
import com.revature.services.CartServiceImpl;

@RestController
@RequestMapping(path="/carts")
@CrossOrigin(allowCredentials = "true", origins = {"http://localhost:4200","http://revproject2frontend.s3-website-us-west-2.amazonaws.com/"})
public class CartController {

	private CartServiceImpl cs;
	
	@Autowired
	public CartController(CartServiceImpl cs) {
		this.cs = cs;
	}
	
	@GetMapping
	public List<Carts> getCartById(HttpServletRequest request) {
		Users u = (Users)request.getSession().getAttribute("user");
		List<Carts> c = cs.get(u.getId());
		return c;
	}
	
	@PostMapping(path="/add")
	public ResponseEntity<Carts> addGame(@RequestBody Games g, HttpServletRequest request) {
		Users u = (Users)request.getSession().getAttribute("user");
		Carts c = new Carts();
		c.setUserId(u);
		c.setGameId(g);
		Carts cart = cs.add(c);
		return ResponseEntity.ok(cart);
	}
	
	@PostMapping(path="/remove")
	public void removeGame(@RequestBody Games g, HttpServletRequest request) {
		Users u = (Users)request.getSession().getAttribute("user");
		Carts c = new Carts();
		c.setUserId(u);
		c.setGameId(g);
		cs.deleteCartItem(c);
		//return ResponseEntity.ok(c);
	}
}
