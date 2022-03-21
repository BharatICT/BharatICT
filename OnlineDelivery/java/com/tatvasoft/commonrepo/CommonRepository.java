package com.tatvasoft.commonrepo;

import com.google.gson.reflect.TypeToken;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.ResourceAccessException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tatvasoft.entity.MstUserRegBo;
import com.tatvasoft.repository.MstUserRegRepository;

@Transactional
@Repository
public class CommonRepository {

	
	
	private final DataSource dataSource;
	private final JdbcTemplate jdbcTemplateObject;

	Gson gson = new GsonBuilder().create();	
	
	@Autowired
	public CommonRepository(DataSource dataSource,JdbcTemplate jdbcTemplateObject) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject=jdbcTemplateObject;
	}

	@Autowired
	MstUserRegRepository mstUserRepo;
	public MstUserRegBo createNewUser(MstUserRegBo userBo)throws Exception
	{
		return mstUserRepo.save(userBo);
	}
	
	public Map<String,Object> verifyUserOnline(String username, String password) throws ResourceAccessException
	{
		Map<String,Object> map=new HashMap<>();
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("PROC_LOGIN_DELIVERY").returningResultSet("out_json", BeanPropertyRowMapper.newInstance(MstUserRegBo.class)).returningResultSet("out_message", BeanPropertyRowMapper.newInstance(String.class));
		SqlParameterSource in = new MapSqlParameterSource().addValue("IN_USERNAME", username).addValue("IN_PASSWORD", password);
		Map<String, Object> out = jdbcCall.execute(in);
		MstUserRegBo[] placelist =  gson.fromJson(out.get("out_json").toString(), MstUserRegBo[].class);    
	    List<MstUserRegBo> list=Arrays.asList(placelist);
		map.put("roleName", "ADMIN");
		map.put("loginStatus", "SUCCESS");
		map.put("returnMessage","SUCCESS");
		map.put("serviceStatus", true);
		map.put("validated", true);
		map.put("status", 200);
		map.put("responseData",list);
		return map;
	}
	
	public MstUserRegBo getUserData(int userId)throws Exception
	{
		MstUserRegBo userBo=mstUserRepo.getById(userId);
		return userBo;
	}
}
