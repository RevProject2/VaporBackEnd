package com.revature.repos;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.beans.Libraries;

@Repository
public interface LibraryDAO extends CrudRepository<Libraries, Integer>{

	@Query(value="select * from project2.libraries where user_id = ?1",nativeQuery = true)
	public List<Libraries> get(int id);
}
