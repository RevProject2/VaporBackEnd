package com.revature.services;

import com.revature.beans.Employees;

public interface EmployeeService extends GenericService<Employees>{
	
	public Employees login(String username, String password);
}
