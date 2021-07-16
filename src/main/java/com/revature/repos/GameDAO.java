package com.revature.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.beans.Games;

@Repository
public interface GameDAO extends CrudRepository<Games, Integer>{

	public Games findByName(String name);

}
