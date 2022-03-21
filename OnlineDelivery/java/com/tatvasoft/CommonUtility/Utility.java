package com.tatvasoft.CommonUtility;

import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

public class Utility {

	public static String getIp(HttpServletRequest request) throws UnknownHostException
	{
		String usrIp = request.getHeader("X-FORWARDED-FOR");
		if(usrIp==null || usrIp == "")
		{
			usrIp = request.getRemoteAddr();
		}
		
		return usrIp;
	}
}
