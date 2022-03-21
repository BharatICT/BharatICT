package com.tatvasoft;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.tatvasoft.commonentity.WebResponseJsonBo;
import com.tatvasoft.entity.MstUserRegBo;

@RunWith(SpringRunner.class)
@SpringBootTest
class OnlineDeliveryApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext context;
	
	ObjectMapper mapper=new ObjectMapper();
	
	@Before
	public void setUp()
	{
		mockMvc=MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void createNewUser() throws Exception
	{
		MstUserRegBo regBo=new MstUserRegBo();
		regBo.setFirstName("Vivek");
		regBo.setMiddleName("DineshBhai");
		regBo.setLastName("Solanki");
		regBo.setLoginName("VIVEK");
		regBo.setPassword("VIVEK");
		regBo.setContactNo("079280623");
		regBo.setMobileNo("9104885472");
		regBo.setRoleId("ROLE_ADMIN");
		
		String jsonRequest=mapper.writeValueAsString(regBo);
		MvcResult result=mockMvc.perform(post("/login/createNewUser").content(jsonRequest).content(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
	
		String resultContent=result.getResponse().getContentAsString();
		WebResponseJsonBo responseBo=mapper.readValue(resultContent, WebResponseJsonBo.class);
		Assert.assertTrue(responseBo.isValidated()==Boolean.TRUE);
	}
}
