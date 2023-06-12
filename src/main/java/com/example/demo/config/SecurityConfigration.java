package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfigration{
	
	private final AuthenticationProvider authenticationProvider;
	private final JwtFilter jwtAuthFilter;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		http
//			.csrf()
//			.disable()
//			.authorizeHttpRequests()
//			.requestMatchers("/api/v1/auth")
//			.permitAll()
//			.anyRequest()
//			.authenticated()
//			.and()
//			.sessionManagement()
//			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//			.and()
//			.authenticationProvider(authenticationProvider)
//			.addFilterBefore(jwtAuthFilter,UsernamePasswordAuthenticationFilter.class);
//		
//		return http.build();
		return http.csrf().disable().cors().disable().authorizeHttpRequests((authorize) -> authorize.requestMatchers("/api/v1/auth/register","/api/v1/auth/authenticate").permitAll()
				.requestMatchers("api/**").authenticated()
				
				
				).sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authenticationProvider(authenticationProvider).addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
		
	}
}
