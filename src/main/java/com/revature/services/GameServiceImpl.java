package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.Games;
import com.revature.repos.GameDAO;

@Service
public class GameServiceImpl implements GameService {

	private GameDAO gdao;
	
	@Autowired
	public GameServiceImpl(GameDAO gdao) {
		this.gdao = gdao;
	}
	
	@Override
	public Games getById(int id) {
		return gdao.findById(id).get();
	}

	@Override
	public Games add(Games t) {
		Games game = gdao.findByName(t.getName());
		if (game == null) {
			return gdao.save(t);
		}
		return null;
	}

	@Override
	public List<Games> getAll() {
		return (List<Games>) gdao.findAll();
	}

	@Override
	public Games update(Games t) {
		return gdao.save(t);
	}

	@Override
	public void deleteById(int id) {
		gdao.deleteById(id);

	}

	@Override
	public List<Games> search(String name) {
		return gdao.search(name);
	}

}
