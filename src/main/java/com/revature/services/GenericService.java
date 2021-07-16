package com.revature.services;

import java.util.List;

public interface GenericService<T> {
	
	public T getById(int id);
	public T add(T t);
	public List<T> getAll();
	public T update(T t);
	public void deleteById(int id);
}
