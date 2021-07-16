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

import com.revature.beans.Games;
import com.revature.services.GameServiceImpl;

@RestController
@RequestMapping(path="/games")
@CrossOrigin(allowCredentials = "true", origins = {"http://localhost:4200","http://revproject2frontend.s3-website-us-west-2.amazonaws.com/"})
public class GameController {
	
	private GameServiceImpl gs;
	
	@Autowired
	public GameController(GameServiceImpl gs) {
		this.gs = gs;
	}

	@GetMapping
	public List<Games> getAllGames(HttpServletRequest request) {
		System.out.println(request.getSession().getId());
		return gs.getAll();
	}
	
	@PostMapping(path="/search")
	public List<Games> search(@RequestBody Games g) {
		return gs.search(g.getName());
	}
	
	@PostMapping
	public ResponseEntity<Games> addGame(@RequestBody Games g) {
		Games game = gs.add(g);
		if (game != null) {
			return ResponseEntity.created(URI.create("http://localhost:8080/games" + game.getId())).build();
		}
		return ResponseEntity.status(409).build();
	}
	
	@PutMapping
	public ResponseEntity<Games> updateGame(@RequestBody Games g) {
		Games game = gs.update(g);
		return ResponseEntity.created(URI.create("http://localhost:8080/games" + game.getId())).build();
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Integer> deleteGameById(@PathVariable("id") Integer id) {
		// Currently we won't have deleting account functionality
		// So this method does not really matter
		// In the future, should add some error handling based on delete
		gs.deleteById(id);
		return new ResponseEntity<>(id, HttpStatus.OK);
	}
}
