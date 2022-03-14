package com.shairy.rest.webservices.restfulwebservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shairy.rest.webservices.restfulwebservices.beans.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
