package com.kiit.FirstSpringboot.security;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.kiit.FirstSpringboot.model.Role;
import com.kiit.FirstSpringboot.model.User;

public class MyUserDecorator implements UserDetails {

	private User user;
	
	public MyUserDecorator(User user)
	{
		this.user=user;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<Role> roles = user.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
         
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
         
        return authorities;

	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		LocalDate accExpDate=user.getAccountExpiryDate();
		LocalDate todaysDate=LocalDate.now();
		if(todaysDate.isAfter(accExpDate))
			return false;
		else
			return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		int accLockStatus=user.getAccountLockedStatus();
				if(accLockStatus==1)
					return true;
				else
					return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		LocalDate credExpDate=user.getCredentialsExpiryDate();
				LocalDate todaysDate=LocalDate.now();
				if(todaysDate.isAfter(credExpDate))
					return false;
				else
					return true;
	}

	@Override
	public boolean isEnabled() {
		int accEnabledStatus=user.getAccountEnabledStatus();
				if(accEnabledStatus==1)
					return true;
				else
					return false;
	}

}
