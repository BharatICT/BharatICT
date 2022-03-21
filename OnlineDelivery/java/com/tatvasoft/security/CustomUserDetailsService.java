package com.tatvasoft.security;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tatvasoft.entity.MstUserRegBo;
import com.tatvasoft.repository.MstUserRegRepository;
@Service
public class CustomUserDetailsService implements UserDetailsService  {

	
	@Autowired
	MstUserRegRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		List<SimpleGrantedAuthority> roles=null;
		MstUserRegBo user = userRepo.findByLoginName(username);
		if(null != user)
		{
			roles = Arrays.asList(new SimpleGrantedAuthority(user.getRoleId()));
			return new User(user.getLoginName(), user.getLoginPassword(), roles);
		}
		throw new UsernameNotFoundException("User not found with username: " + username);
	}
}
