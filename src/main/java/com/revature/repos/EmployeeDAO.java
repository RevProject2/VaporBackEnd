package com.revature.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.beans.Employees;

@Repository
public interface EmployeeDAO extends CrudRepository<Employees, Integer>{

	public Employees findByUsername(String username);
}
