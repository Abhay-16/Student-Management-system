package com.example.demo.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 if(username.equals("admin")) {
			 return User.builder()
					 .username("admin")
					 .password(new BCryptPasswordEncoder().encode("admin@123"))
					 .roles("ADMIN")
					 .build();
		 }
		 
		throw new UsernameNotFoundException("User not found");
	}

	
}
