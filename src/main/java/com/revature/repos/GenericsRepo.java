package com.revature.repos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public interface GenericsRepo<T> {

	//Create
	public T add(T t);
	
	//Read
	public T getById(Integer id);
	
	//Using a map here to grab all the key/value pairs in the database
	public Map<Integer, T> getAll();
	
	//Update
	public boolean update(T t);
	
	//Delete
	public boolean delete(T t);

	/*
	 * This is so I don't have to keep retyping the setters, but may become
	 * irrelevant when we switch to hibernate.
	 */
	public T make(ResultSet rs) throws SQLException;
}
