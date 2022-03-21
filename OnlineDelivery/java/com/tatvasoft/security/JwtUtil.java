package com.tatvasoft.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Service
public class JwtUtil 
{
	private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtil.class);
	private String secret;
	private int jwtExpirationInMs;
	
	@Value("${jwt.secret}")
	public void setSecret(String secret) 
	{
		this.secret = secret;
	}
	
	@Value("${jwt.jwtExpirationInMs}")
	public void setJwtExpirationInMs(int jwtExpirationInMs) 
	{
		this.jwtExpirationInMs = jwtExpirationInMs;
	}
	
	// generate token for user
	public String generateToken(UserDetails userDetails) 
	{
		LOGGER.info("JwtUtil :	generateToken	:	START");
		Map<String, Object> claims = new HashMap<>();
		Collection<? extends GrantedAuthority> roles = userDetails.getAuthorities();
		if (roles.contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) 
		{
			claims.put("isAdmin", true);
		}
		else if (roles.contains(new SimpleGrantedAuthority("ROLE_USER"))) 
		{
			claims.put("isUser", true);
		}
		else if (roles.contains(new SimpleGrantedAuthority("COUNSELLOR"))) 
		{
			claims.put("isCounsellor", true);
		}
		else if (roles.contains(new SimpleGrantedAuthority("CO-ORDINATOR"))) 
		{
			claims.put("isCordinator", true);
		}
		else if (roles.contains(new SimpleGrantedAuthority("SYSADMIN"))) 
		{
			claims.put("isSysadmin", true);
		}
		LOGGER.info("JwtUtil :	generateToken	:	END");
		return doGenerateToken(claims, userDetails.getUsername());
		
	}

	private String doGenerateToken(Map<String, Object> claims, String username) 
	{
		return Jwts.builder().setClaims(claims).setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
		
	}
	
	public boolean validateToken(String authToken) 
	{
		try 
		{
			// Jwt token has not been tampered with
			Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(authToken);
			return true;
		} 
		catch (SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException ex) 
		{
			throw new BadCredentialsException("INVALID_CREDENTIALS", ex);
		}
		catch (ExpiredJwtException ex) 
		{
			throw  ex;
		}
	}
	
	public String getUsernameFromToken(String token) 
	{
		Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		return claims.getSubject();
	}
	
	public List<SimpleGrantedAuthority> getRolesFromToken(String authToken) 
	{
		List<SimpleGrantedAuthority> roles = null;
		Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(authToken).getBody();
		Boolean isAdmin = claims.get("isAdmin", Boolean.class);
		Boolean isUser = claims.get("isUser", Boolean.class);
		Boolean isCounsellor = claims.get("isCounsellor", Boolean.class);
		Boolean isCordinator = claims.get("isCordinator", Boolean.class);
		Boolean isSysadmin = claims.get("isSysadmin", Boolean.class);
		if (isAdmin != null && isAdmin == true) 
		{
			roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}
		if (isUser != null && isUser == true) 
		{
			roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
		}
		if (isCounsellor != null && isCounsellor == true) 
		{
			roles = Arrays.asList(new SimpleGrantedAuthority("COUNSELLOR"));
		}
		if (isCordinator != null && isCordinator == true) 
		{
			roles = Arrays.asList(new SimpleGrantedAuthority("CO-ORDINATOR"));
		}
		if (isSysadmin != null && isSysadmin == true) 
		{
			roles = Arrays.asList(new SimpleGrantedAuthority("SYSADMIN"));
		}
		return roles;
	}
}
