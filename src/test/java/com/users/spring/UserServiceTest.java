package com.users.spring;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.users.spring.entity.User;
import com.users.spring.repository.UserRepository;
import com.users.spring.service.UserService;

@SpringBootTest
public class UserServiceTest {

	@Autowired
	private UserService service;

	@MockBean
	private UserRepository repository;

	@Test
	@DisplayName("test Get Users method")
	public void testGetUsers() {
		when(repository.findAll()).thenReturn(Stream
				.of(new User(101, "ranjit", "vizag"), new User(201, "kiran", "chennai")).collect(Collectors.toList()));
		assertEquals(2, service.getUsers().size());
	}

	@Test
	@DisplayName("Test Get User By Address method")
	public void testGetUserByAddress() {
		// GIVEN
		String address = "Hyderabad";
		// WHEN
		when(repository.findByAddress(address))
				.thenReturn(Stream.of(new User(109, "siva", "Hyderabad")).collect(Collectors.toList()));

		assertEquals(1, service.getUserByAddress(address).size());
	}

}
