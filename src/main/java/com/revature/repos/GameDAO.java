package com.revature.repos;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.beans.Games;

@Repository
public interface GameDAO extends CrudRepository<Games, Integer>{
	
	public Games findByName(String name);
	@Query(value="select * from project2.games where name ilike %?1%", nativeQuery = true)
	public List<Games> search(String name);

}
