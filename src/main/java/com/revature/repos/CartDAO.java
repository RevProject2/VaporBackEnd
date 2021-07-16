package com.revature.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.beans.CartID;
import com.revature.beans.Carts;

@Repository
public interface CartDAO extends CrudRepository<Carts, CartID>{

}
