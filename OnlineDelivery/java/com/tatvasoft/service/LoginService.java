package com.tatvasoft.service;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.tatvasoft.commonentity.WebResponseJsonBo;

//@Component
public interface LoginService {

	WebResponseJsonBo createNewUser(Map<String,Object> map)throws Exception;
	WebResponseJsonBo verifyUserOnline(String userName,String password)throws Exception;
}
