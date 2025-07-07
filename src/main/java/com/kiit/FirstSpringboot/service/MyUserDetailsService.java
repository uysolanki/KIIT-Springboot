package com.kiit.FirstSpringboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kiit.FirstSpringboot.model.User;
import com.kiit.FirstSpringboot.repository.UserRepository;
import com.kiit.FirstSpringboot.security.MyUserDecorator;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=userRepository.findByUsername(username);
		
		if(user==null)
			throw new UsernameNotFoundException("User does not exists");
		
		return new MyUserDecorator(user);
	}

}
