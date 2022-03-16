package com.shairy.rest.webservices.restfulwebservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shairy.rest.webservices.restfulwebservices.beans.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

}
