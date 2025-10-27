package com.example.booking.util;

import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	private final Key key = Keys.hmacShaKeyFor(System.getenv()
			.getOrDefault("JWT_SECRET", "my 64 bit lengthy secret key configure and chnage it").getBytes());
	private final long ttl = 1000l * 60 * 60;

//	public String generateToken(String username, Map<String, Object> claims) {
//		return Jwts.builder().setSubject(username).addClaims(claims)
//				.setExpiration(new Date(System.currentTimeMillis() + ttl)).signWith(key).compact();
//	}
	
	public String generateToken(String username, String role) {
	    Map<String, Object> claims = new HashMap<>();
	    claims.put("role", role);
	    claims.put("iss", "booking-service");
	    claims.put("iat", new Date());

	    return Jwts.builder()
	            .setSubject(username)
	            .addClaims(claims)
	            .setExpiration(new Date(System.currentTimeMillis() + ttl))
	            .signWith(key)
	            .compact();
	}

	public Collection<? extends GrantedAuthority> extractAuthorities(String token) {
//		Claims claims = extractAllClaims(token);
		Claims claims = (Claims) parse(token);
		String role = claims.get("role", String.class);
		return List.of(new SimpleGrantedAuthority("ROLE_" + role));
	}

	public Jws<Claims> parse(String jwt) {
		return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt);
	}
}
