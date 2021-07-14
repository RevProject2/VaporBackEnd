package com.revature.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.beans.Users;

@Repository
public interface UserDAO extends CrudRepository<Users, Integer>{

	public Users findByUsername(String username);
}
