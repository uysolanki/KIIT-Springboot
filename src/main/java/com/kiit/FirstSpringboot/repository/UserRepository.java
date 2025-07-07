package com.kiit.FirstSpringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kiit.FirstSpringboot.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>
{
			public User findByUsername(String x);
}
