package com.tatvasoft.service;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.tatvasoft.commonentity.WebResponseJsonBo;
import com.tatvasoft.entity.MstUserRegBo;

@Component
public interface OnlineService {

	public WebResponseJsonBo getUserData(Map<String,Object> map)throws Exception;
}
