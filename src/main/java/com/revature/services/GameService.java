package com.revature.services;

import java.util.List;

import com.revature.beans.Games;

public interface GameService extends GenericService<Games>{

	public List<Games> search(String name);
}
