package com.revature.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
}
