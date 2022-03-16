package com.shairy.rest.webservices.restfulwebservices.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriBuilderFactory;

import com.shairy.rest.webservices.restfulwebservices.beans.Post;
import com.shairy.rest.webservices.restfulwebservices.beans.User;
import com.shairy.rest.webservices.restfulwebservices.exceptions.UserNotFoundException;
import com.shairy.rest.webservices.restfulwebservices.repository.PostRepository;
import com.shairy.rest.webservices.restfulwebservices.repository.UserRepository;

import jdk.jshell.spi.ExecutionControl.UserException;

/**
 * 
 * @author rishabhshairy
 *
 */
@RestController
@RequestMapping("jpa")
public class UserJPAController {
	private static Logger logger = LoggerFactory.getLogger(UserJPAController.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	@GetMapping("/users")
	public List<User> retrieveAll() {
		return userRepository.findAll();
	}

	@GetMapping("/users/{id}")
	public EntityModel<User> findById(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		logger.debug(user.toString());
		if (user.isEmpty()) {
			throw new UserNotFoundException("id = " + id);

		}
		// Adding link to get all users
		EntityModel<User> model = EntityModel.of(user.get());
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAll());
		model.add(link.withRel("all-users"));
		return model;
	}

	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.saveAndFlush(user);

		// returning location of saved user
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isEmpty()) {
			throw new UserNotFoundException("id = " + id);
		}
		userRepository.deleteById(id);
		return ResponseEntity.ok(user);
	}

	/**
	 * Request related to post
	 */

	@GetMapping("/users/{id}/posts")
	public List<Post> findAllPostByUser(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isEmpty()) {
			throw new UserNotFoundException("id - " + id);
		}

		return user.get().getPosts();
	}

	@PostMapping("/users/{id}/posts")
	public ResponseEntity<Object> createPostForUser(@PathVariable int id, @RequestBody Post post) {
		Optional<User> userOptional = userRepository.findById(id);

		if (userOptional.isEmpty()) {
			throw new UserNotFoundException("id - " + id);
		}

		User user = userOptional.get();
		post.setUser(user);
		postRepository.saveAndFlush(post);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(post.getId())
				.toUri();
		return new ResponseEntity<Object>(post, HttpStatus.CREATED);
	}
}
