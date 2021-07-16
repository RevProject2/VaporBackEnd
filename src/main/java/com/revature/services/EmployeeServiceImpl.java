package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.Employees;
import com.revature.repos.EmployeeDAO;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeDAO edao;
	
	@Autowired
	public EmployeeServiceImpl(EmployeeDAO edao) {
		this.edao = edao;
	}
	
	@Override
	public Employees getById(int id) {
		return edao.findById(id).get();
	}

	@Override
	public Employees add(Employees t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employees> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employees update(Employees t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Employees login(String username, String password) {
		Employees e = edao.findByUsername(username);
		if (e != null && password.equals(e.getPassword())) {
			return e;
		}
		return null;
	}

}
