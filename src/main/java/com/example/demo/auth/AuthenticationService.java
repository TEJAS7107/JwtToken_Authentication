package com.example.demo.auth;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.Model.Role;
import com.example.demo.Model.User;
import com.example.demo.config.JwtService;
import com.example.demo.dao.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	
//	@Autowired
//	User u;
//	RegisterRequest r;
	
	private final UserRepo repo;
	//private final User u = null;
	private final PasswordEncoder pass;
	
	
	private final JwtService jwtService;
	
	private final AuthenticationManager AuthMan;

	public AuthenticationResponse register(RegisterRequest rr) {
		var user = User.builder()
				.firstname(rr.getFirstname())
				.lastname(rr.getLastname())
				.email(rr.getEmail())
				.password(pass.encode(rr.getPassword()))
				.role(Role.user)
				.build();
		repo.save(user);
		//return null;
		var jwtToken = jwtService.generateToken(user);
		// TODO Auto-generated method stub
		System.out.println(jwtService.generateToken(user));
		return AuthenticationResponse.builder().token(jwtToken).build();
		
		//return null;
	}

	public AuthenticationResponse authenticate(AuthenticationRequest ar) {
		// TODO Auto-generated method stub
		AuthMan.authenticate(new UsernamePasswordAuthenticationToken(ar.getEmail(), ar.getPassword()));
		
		var user = repo.findByEmail(ar.getEmail()).orElseThrow();
		var jwtToken = jwtService.generateToken(user);
		// TODO Auto-generated method stub
		return AuthenticationResponse.builder().token(jwtToken).build();
	}

}
