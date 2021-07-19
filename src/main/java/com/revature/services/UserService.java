package com.revature.services;

import java.util.List;

import com.revature.beans.Purchases;
import com.revature.beans.Users;

public interface UserService extends GenericService<Users> {
//	public Users getUserById(int id);
//	public Users addUser(Users u);
//	public List<Users> getAllUsers();
//	public Users updateUser(Users u);
//	public void deleteUserById(int id);
	public Users login(String username, String password);

	public boolean purchaseCart(Integer id);

	public boolean deposit(Users u, Double amount);

	public List<Purchases> getHistory(Integer id);

}
