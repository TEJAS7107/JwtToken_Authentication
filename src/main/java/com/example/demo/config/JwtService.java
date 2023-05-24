package com.example.demo.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
	
	private static final String SECRET_KEY = "743677397A244326452948404D635166546A576E5A7234753778214125442A47";
	
	
	public<T> T extractClaims(String token,Function<Claims, T> claimsResolver) {
		
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
		
	}
	
	public String extractUsername(String token) {
		// TODO Auto-generated method stub
		return extractClaims(token, Claims::getSubject);
	}
	
	
	private Claims extractAllClaims(String token) {
		return Jwts
					.parserBuilder()
					.setSigningKey(getSigninKey())
					.build()
					.parseClaimsJws(token)
					.getBody();
	}
	
	public String generateToken(UserDetails userDetails) {
		
		return generateToken(new HashMap<>(), userDetails);
		
		
	}
	
	public boolean isTokenVaild(String token,UserDetails userDetails) {
		
		String username = extractUsername(token);
		return  (username.equalsIgnoreCase(userDetails.getUsername())&&!isTokenExpired(token));
			
		
	}
	
	private boolean isTokenExpired(String token) {
		// TODO Auto-generated method stub
		return extractExpirationDate(token).before(new Date());
	}

	private Date extractExpirationDate(String token) {
		// TODO Auto-generated method stub
		return extractClaims(token,Claims::getExpiration);
	}

	public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
		
		return
				Jwts
				.builder()
				.setClaims(extraClaims)
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000*60*24))
				.signWith(getSigninKey(),SignatureAlgorithm.HS256)
				.compact();
		
		
	}

	private Key getSigninKey() {
		// TODO Auto-generated method stub
		byte[] bb  = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(bb);
	}
}
