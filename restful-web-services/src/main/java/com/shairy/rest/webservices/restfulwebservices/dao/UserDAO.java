package com.shairy.rest.webservices.restfulwebservices.dao;

import java.util.List;
import java.util.Optional;

import com.shairy.rest.webservices.restfulwebservices.beans.User;

public interface UserDAO {
	List<User> findAll();

	User save(User user);

	Optional<User> findOne(int id);
	
	User deleteById(int id);

}
