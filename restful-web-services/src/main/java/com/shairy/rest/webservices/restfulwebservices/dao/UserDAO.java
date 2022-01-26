package com.shairy.rest.webservices.restfulwebservices.dao;

import java.util.List;

import com.shairy.rest.webservices.restfulwebservices.beans.User;

public interface UserDAO {
	List<User> findAll();

	User save(User user);

	User findOne(int id);

}
