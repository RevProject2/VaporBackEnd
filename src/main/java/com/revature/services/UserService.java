package com.revature.services;

import java.util.List;

import com.revature.beans.Users;

public interface UserService {
	public Users getUserById(int id);
	public Users addUser(Users u);
	public List<Users> getAllUsers();
	public Users updateUser(Users u);
	public void deleteUserById(int id);
	public Users login(String username, String password);
}
