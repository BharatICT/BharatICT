package com.tatvasoft.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tatvasoft.CommonUtility.Utility;
import com.tatvasoft.commonentity.AuthenticationRequest;
import com.tatvasoft.commonentity.AuthenticationResponse;
import com.tatvasoft.commonentity.WebResponseJsonBo;
import com.tatvasoft.constants.Constants;
import com.tatvasoft.entity.MstUserRegBo;
import com.tatvasoft.exception.ResourceNotFoundException;
import com.tatvasoft.security.CustomUserDetailsService;
import com.tatvasoft.security.JwtUtil;
import com.tatvasoft.service.LoginService;

@RestController
@RequestMapping("/login")
public class LoginController {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

	
	@Autowired
	LoginService loginService;
	
	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtil jwtTokenUtil;
	
	@PostMapping("/verifyOnlineLogin")
	public WebResponseJsonBo verifyOnlineLogin(@Valid @RequestBody AuthenticationRequest userBo,HttpServletRequest request,HttpServletResponse response)throws ResourceNotFoundException,Exception
	{
		WebResponseJsonBo resBo=loginService.verifyUserOnline(userBo.getUsername(),userBo.getEncripted_password());
		final UserDetails userDetails = userDetailsService.loadUserByUsername(userBo.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		resBo.setToken(token);
		return resBo;
	}
	@PostMapping("/createNewUser")
	public WebResponseJsonBo createNewUser(@Valid @RequestBody MstUserRegBo userBo,HttpServletRequest req,HttpServletResponse res)throws Exception
	{
			Map<String,Object> map=new HashMap<>();
			userBo.setCrtDate(new Date());
			userBo.setCrIp(Utility.getIp(req));
			userBo.setCrtUser("ADMIN");
			userBo.setStatus(Constants.Active);
			userBo.setLoginPassword(bcryptEncoder.encode(userBo.getPassword()));
			map.put("webBo", userBo);
			WebResponseJsonBo resBo=loginService.createNewUser(map);
			return resBo;
	}
}
