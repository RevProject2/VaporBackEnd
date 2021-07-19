package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.Genres;
import com.revature.services.GenreService;

@RestController
@RequestMapping(path="/genres")
@CrossOrigin(allowCredentials = "true", origins = {"http://localhost:4200","http://revproject2frontend.s3-website-us-west-2.amazonaws.com/"})
public class GenreController {

	private GenreService gs;
	
	@Autowired
	public GenreController(GenreService gs) {
		this.gs = gs;
	}
	
	@GetMapping
	public List<Genres> getAll() {
		return gs.getAll();
	}
}
