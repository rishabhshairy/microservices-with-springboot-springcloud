package com.shairy.rest.webservices.restfulwebservices.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.shairy.rest.webservices.restfulwebservices.beans.User;

@Component
public class UserDAOImpl implements UserDAO {

	private static List<User> users = new ArrayList<>();
	private static int userCount = 3;
	static {
		users.add(new User(1, "Jack", new Date()));
		users.add(new User(2, "John", new Date()));
		users.add(new User(3, "Sara", new Date()));
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return users;
	}

	@Override
	public User save(User user) {
		// TODO Auto-generated method stub
		if (user.getId() == null) {
			user.setId(++userCount);
		}
		users.add(user);
		return user;
	}

	@Override
	public User findOne(int id) {
		// TODO Auto-generated method stub
		Optional<User> user = users.stream().filter(obj -> obj.getId().equals(id)).findFirst();
		return user.get();
	}

}
