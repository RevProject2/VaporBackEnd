package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.Carts;
import com.revature.repos.CartDAO;

@Service
public class CartServiceImpl implements CartService {

	private CartDAO cdao;
	
	@Autowired
	public CartServiceImpl(CartDAO cdao) {
		this.cdao = cdao;
	}
	@Override
	public Carts getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Carts add(Carts t) {
		//return cdao.add(t.getUserId().getId(), t.getGameId().getId());
		return cdao.save(t);
	}

	@Override
	public List<Carts> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Carts update(Carts t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(int id) {
		cdao.deleteById(id);
	}
	@Override
	public void deleteCartItem(Carts c) {
		//cdao.delete(c.getUserId().getId(), c.getGameId().getId());
		Carts cart = cdao.findByUserIdAndGameId(c.getUserId(), c.getGameId());
		System.out.println(cart);
		System.out.println(cart);
		System.out.println(cart);
		System.out.println(cart);
		if (cart != null) {
			cdao.delete(cart);
		}
		
	}
	@Override
	public List<Carts> get(Integer id) {
		return cdao.get(id);
	}

}
