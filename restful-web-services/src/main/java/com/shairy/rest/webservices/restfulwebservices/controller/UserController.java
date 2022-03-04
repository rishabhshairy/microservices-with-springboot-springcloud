package com.shairy.rest.webservices.restfulwebservices.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.shairy.rest.webservices.restfulwebservices.beans.User;
import com.shairy.rest.webservices.restfulwebservices.dao.UserDAO;
import com.shairy.rest.webservices.restfulwebservices.exceptions.UserNotFoundException;

@RestController
public class UserController {

	private Logger logger = org.slf4j.LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserDAO userDAO;

	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userDAO.findAll();
	}

	@GetMapping("/users/{id}")
	public User findById(@PathVariable int id) {
		Optional<User> user = userDAO.findOne(id);
		logger.debug(user.toString());
		if (user.isEmpty()) {
			throw new UserNotFoundException("id = " + id);

		}
		return user.get();
	}

	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userDAO.save(user);

		// returning location of saved user
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/user/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable int id){
		User user = userDAO.deleteById(id);
		if (user==null) {
			throw new UserNotFoundException("id = "+id);
		}
		return ResponseEntity.ok(user);
	}
}
