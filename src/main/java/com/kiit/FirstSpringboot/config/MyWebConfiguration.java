package com.kiit.FirstSpringboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.kiit.FirstSpringboot.service.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class MyWebConfiguration// extends WebSecurityConfigurerAdapter
{
 
//	@Override			//Authentication
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////		auth.inMemoryAuthentication()
//		.withUser("jetha")
//		.password("jetha123")  //plain text   $2a454DFdf%^&%^gfgfghf576567565   bcrypt
//		.authorities("ADMIN")
//		.and()
//		.withUser("bagha")
//		.password("bagha123")
//		.authorities("USER")
//		.and()
//		.withUser("nattu")
//		.password("nattu123")
//		.authorities("USER");
//		
//		auth.authenticationProvider(myAuthenticationProvider());  //single point of contact for entire authentication - contractractor
//}
 
@Bean	
public AuthenticationProvider myAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(mySetUserDetailsService());  //helpers
		daoAuthenticationProvider.setPasswordEncoder(mySetPasswordEncoder());
		return daoAuthenticationProvider;	
	}

@Bean	
public PasswordEncoder mySetPasswordEncoder() {
	return new BCryptPasswordEncoder();
}

@Bean	
public UserDetailsService mySetUserDetailsService() {
	return new MyUserDetailsService();
}

//@Override				//Autheorisation
//	protected void configure(HttpSecurity http) throws Exception {
//	 http.authorizeRequests()
//     .antMatchers("/","/addProductForm","403").hasAnyAuthority("USER","ADMIN")
//     .antMatchers("/updateProductForm/**","/deleteProduct/**").hasAuthority("ADMIN")
//     .anyRequest().authenticated()
//     .and()
//     .formLogin().loginProcessingUrl("/login").successForwardUrl("/").permitAll()
//     .and()
//     .logout().logoutSuccessUrl("/login").permitAll()
//     .and()
//     .exceptionHandling().accessDeniedPage("/403")
//     .and()
//     .cors().and().csrf().disable();
//
//	}
 
// @Bean
//	public PasswordEncoder getPasswordEncoder()
//	{
//		return NoOpPasswordEncoder.getInstance();
//	}

@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	http.authorizeRequests()
    .requestMatchers("/","/addProductForm","403").hasAnyAuthority("USER","ADMIN")
    .requestMatchers("/updateProductForm/**","/deleteProduct/**").hasAuthority("ADMIN")
    .anyRequest().authenticated()
    .and()
    .formLogin().loginProcessingUrl("/login").successForwardUrl("/").permitAll()
    .and()
    .logout().logoutSuccessUrl("/login").permitAll()
    .and()
    .exceptionHandling().accessDeniedPage("/403")
    .and()
    .cors().and().csrf().disable();
    
    
    http.authenticationProvider(myAuthenticationProvider());
    return http.build();
}
}
