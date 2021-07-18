package com.revature.repos;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.revature.beans.Users;

@Repository
public interface UserDAO extends CrudRepository<Users, Integer>{

	public Users findByUsername(String username);
	@Transactional
	@Modifying
	@Query(value="insert into project2.libraries values (default,?1,?2)", nativeQuery = true)
	public void addToLibrary(int uid, int gid);
}
