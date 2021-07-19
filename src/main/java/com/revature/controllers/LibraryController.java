package com.revature.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.Libraries;
import com.revature.beans.Users;
import com.revature.services.LibraryServiceImpl;

@RestController
@RequestMapping(path="/libraries")
@CrossOrigin(allowCredentials = "true", origins = {"http://localhost:4200","http://revproject2frontend.s3-website-us-west-2.amazonaws.com/"})
public class LibraryController {

	private LibraryServiceImpl ls;
	
	@Autowired
	public LibraryController(LibraryServiceImpl ls) {
		this.ls = ls;
	}
	
	@GetMapping
	public ResponseEntity<List<Libraries>> getLibrary(HttpServletRequest request) {
		Users u = (Users)request.getSession().getAttribute("user");
		if (u != null) {
			List<Libraries> l = ls.get(u.getId());
			return new ResponseEntity<>(l, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
}
