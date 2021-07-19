package com.revature.repos;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.beans.Purchases;

@Repository
public interface PurchaseDAO extends CrudRepository<Purchases, Integer>{

	@Query(value="select * from project2.purchases where user_id = ?1",nativeQuery = true)
	public List<Purchases> get(int id);
}
