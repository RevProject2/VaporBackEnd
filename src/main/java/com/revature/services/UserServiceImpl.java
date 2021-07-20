package com.revature.services;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.Carts;
import com.revature.beans.Purchases;
import com.revature.beans.Users;
import com.revature.repos.CartDAO;
import com.revature.repos.PurchaseDAO;
import com.revature.repos.UserDAO;

@Service
public class UserServiceImpl implements UserService {

	private UserDAO udao;
	private CartDAO cdao;
	private PurchaseDAO pdao;
	
	@Autowired
	public UserServiceImpl(UserDAO udao, CartDAO cdao, PurchaseDAO pdao) {
		this.udao = udao;
		this.cdao = cdao;
		this.pdao = pdao;
	}
	
	@Override
	public Users getById(int id) {
		return udao.findById(id).get();
	}

	@Override
	public Users add(Users u) {
		Users user = udao.findByUsername(u.getUsername());
		if (user == null) {
			return udao.save(u);
		}
		return null;
	}

	@Override
	public List<Users> getAll() {
		return (List<Users>) udao.findAll();
	}

	@Override
	public Users update(Users u) {
		return udao.save(u);
	}

	@Override
	public void deleteById(int id) {
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

	@Override
	public boolean purchaseCart(Integer id) {
		List<Carts> cart = cdao.getCart(id);
		double cart_price = 0;
		for (Carts c : cart) {
			cart_price += c.getGameId().getPrice();
		}
		Users u = udao.findById(id).get();
		if (u != null && u.getBalance() >= cart_price) {
			for(Carts c : cart) {
				udao.addToLibrary(id, c.getGameId().getId());
				Date d = new Date(System.currentTimeMillis());
				udao.addToPurchaseHistory(id, c.getGameId().getId(), d);
				cdao.delete(c);
			}
			u.setBalance(u.getBalance() - cart_price);
			udao.save(u);
		} else {
			return false;
		}
		return true;
	}

	@Override
	public boolean deposit(Users u, Double amount) {
		if (amount != null && amount > 0) {
			u.setBalance(u.getBalance() + amount);
			udao.save(u);
			return true;
		}
		return false;
	}

	@Override
	public List<Purchases> getHistory(Integer id) {
		return pdao.get(id);
	}


}
