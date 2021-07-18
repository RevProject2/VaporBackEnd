package com.revature.repos;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.beans.Carts;
import com.revature.beans.Games;
import com.revature.beans.Users;

@Repository
public interface CartDAO extends CrudRepository<Carts, Integer>{

	@Query(value="select * from project2.carts where user_id = ?1",nativeQuery = true)
	public List<Carts> get(int id);
	public Carts findByUserIdAndGameId(Users u, Games g);
	@Query(value="select * from project2.carts where user_id = ?1", nativeQuery = true)
	public List<Carts> getCart(int id);
}
