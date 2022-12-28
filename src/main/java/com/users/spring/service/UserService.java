package com.users.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.users.spring.entity.User;
import com.users.spring.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public User addUser(User user) {
		return repository.save(user);
	}

	public List<User> getUsers() {
		return repository.findAll();
	}

	public List<User> getUserByAddress(String address) {
		return repository.findByAddress(address);
	}

	public void deleteUser(User user) {
		repository.delete(user);
	}
}
