package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.Users;
import com.revature.repos.UserDAO;

@Service
public class UserServiceImpl implements UserService {

	private UserDAO udao;
	
	@Autowired
	public UserServiceImpl(UserDAO udao) {
		this.udao = udao;
	}
	
	@Override
	public Users getUserById(int id) {
		return udao.findById(id).get();
	}

	@Override
	public Users addUser(Users u) {
		Users user = udao.findByUsername(u.getUsername());
		if (user != null) {
			return udao.save(u);
		}
		return null;
	}

	@Override
	public List<Users> getAllUsers() {
		return (List<Users>) udao.findAll();
	}

	@Override
	public Users updateUser(Users u) {
		return udao.save(u);
	}

	@Override
	public void deleteUserById(int id) {
		udao.deleteById(id);

	}

	@Override
	public Users login(String username, String password) {
		Users u = udao.findByUsername(username);
		if (u != null && password.equals(u.getPassword())) {
			return u;
		}
		return null;
	}

}