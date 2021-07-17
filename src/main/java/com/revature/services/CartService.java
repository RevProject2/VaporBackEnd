package com.revature.services;

import java.util.List;

import com.revature.beans.Carts;

public interface CartService extends GenericService<Carts>{

	public void deleteCartItem(Carts c);
	public List<Carts> get(Integer id);
}
