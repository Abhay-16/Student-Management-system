package com.example.demo.securityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.Customizer;

import com.example.demo.ServiceImpl.CustomUserDetailsService;
import com.example.demo.utils.JwtFilter;
import com.example.demo.utils.JwtUtils;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    public JwtFilter jwtFilter;
    
	@Autowired
	public CustomUserDetailsService customUserDetailsService;
	
	 @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	
	@Bean 
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
				.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(request -> request
						.requestMatchers("/auth/**").permitAll()
						.anyRequest().authenticated())
				.httpBasic(Customizer.withDefaults())
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

		return httpSecurity.build();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}
	@Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(customUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }
	
}
