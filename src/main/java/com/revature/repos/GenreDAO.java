package com.revature.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.beans.Genres;

@Repository
public interface GenreDAO extends CrudRepository<Genres, String>{

}
