package com.kiit.FirstSpringboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class MyWebConfiguration extends WebSecurityConfigurerAdapter
{
 
	@Override			//Authentication
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser("jetha")
		.password("jetha123")
		.authorities("ADMIN")
		.and()
		.withUser("bagha")
		.password("bagha123")
		.authorities("USER")
		.and()
		.withUser("nattu")
		.password("nattu123")
		.authorities("USER");
}
 
 @Override				//Autheorisation
	protected void configure(HttpSecurity http) throws Exception {
	 http.authorizeRequests()
     .antMatchers("/","/addProductForm","403").hasAnyAuthority("USER","ADMIN")
     .antMatchers("/updateProductForm/**","/deleteProduct/**").hasAuthority("ADMIN")
     .anyRequest().authenticated()
     .and()
     .formLogin().loginProcessingUrl("/login").successForwardUrl("/").permitAll()
     .and()
     .logout().logoutSuccessUrl("/login").permitAll()
     .and()
     .exceptionHandling().accessDeniedPage("/403")
     .and()
     .cors().and().csrf().disable();

	}
 
 @Bean
	public PasswordEncoder getPasswordEncoder()
	{
		return NoOpPasswordEncoder.getInstance();
	}

	
}
