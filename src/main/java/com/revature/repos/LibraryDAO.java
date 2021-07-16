package com.revature.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.beans.CartID;
import com.revature.beans.Libraries;

@Repository
public interface LibraryDAO extends CrudRepository<Libraries, CartID>{

}
