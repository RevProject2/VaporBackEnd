package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.Genres;
import com.revature.repos.GenreDAO;

@Service
public class GenreServiceImpl implements GenreService {
	
	private GenreDAO gdao;
	
	@Autowired
	public GenreServiceImpl(GenreDAO gdao) {
		this.gdao = gdao;
	}

	@Override
	public Genres getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Genres add(Genres t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Genres> getAll() {
		return (List<Genres>) gdao.findAll();
	}

	@Override
	public Genres update(Genres t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub

	}

}
