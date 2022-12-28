package com.users.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.users.spring.entity.User;
import com.users.spring.service.UserService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class UserController {

	@Autowired
	private UserService service;

	
	@Operation(summary = "This method creates a new user!")
	@PostMapping("/save")
	public ResponseEntity<User> save(@RequestBody User user) {

		return new ResponseEntity<User>(service.addUser(user), HttpStatus.CREATED); // 201
	}

	@GetMapping("/getUsers")
	public ResponseEntity<List<User>> findAllUsers() {
		return new ResponseEntity<List<User>>(service.getUsers(), HttpStatus.OK);
	}

	@GetMapping("/getUsersByAddress/{address}")
	public List<User> findUsersByAddress(@PathVariable String address) {
		return service.getUserByAddress(address);
	}

	@DeleteMapping("/remove")
	public User removeUser(@RequestBody User user) {
		service.deleteUser(user);
		return user;
	}

}
