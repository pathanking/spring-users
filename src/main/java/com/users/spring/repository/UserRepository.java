package com.users.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.users.spring.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	List<User> findByAddress(String address);

}
