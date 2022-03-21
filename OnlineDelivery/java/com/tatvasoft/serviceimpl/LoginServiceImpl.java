package com.tatvasoft.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Tuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tatvasoft.commonentity.WebResponseJsonBo;
import com.tatvasoft.commonrepo.CommonRepository;
import com.tatvasoft.constants.Constants;
import com.tatvasoft.entity.MstUserRegBo;
import com.tatvasoft.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService{

	java.util.function.Predicate<String> stringCheck= s -> s != null && s.length() != 0;
	java.util.function.Predicate<Integer> intCheck= s -> s != null && s.intValue() != 0;
	java.util.function.Predicate<List<Tuple>> listTSizeCheck= list -> list != null && list.size() > 0; 
	
	@Autowired
	CommonRepository commonRepository;
	
	
	@Override
	public WebResponseJsonBo createNewUser(Map<String, Object> map) throws Exception {
		
		WebResponseJsonBo returnMap=new WebResponseJsonBo();
		MstUserRegBo userBo=(MstUserRegBo)map.get("webBo");
		
		if(userBo!=null)
		{
			 if(	stringCheck.test(userBo.getFirstName()) && stringCheck.test(userBo.getMiddleName()) && stringCheck.test(userBo.getLastName())
				 &&	stringCheck.test(userBo.getLoginName()) && stringCheck.test(userBo.getEmailId()) && stringCheck.test(userBo.getMobileNo()) 
				 && stringCheck.test(userBo.getContactNo())
				)
				{
					MstUserRegBo persistBo=commonRepository.createNewUser(userBo);
					if(intCheck.test(persistBo.getUserId()))
					{	
						returnMap.setApplication_id(persistBo.getUserId().toString());
						returnMap.setReturn_message("User details successfully submitted");
						returnMap.setValidated(true);
						returnMap.setJsonListData(persistBo);
					}
					else
					{
						returnMap.setReturn_message("Error in save User Registration Data!");
						returnMap.setStatus(Constants.ERROR_CODE);
						returnMap.setValidated(false);
					}
				}
			 else
			 {
				 throw new RuntimeException("Please fill all mandatory fields.");
			 }
		}
		else
		{
			 throw new RuntimeException("Please fill all mandatory fields.");
		}
		return returnMap;
	}


	@Override
	public WebResponseJsonBo verifyUserOnline(String userName,String password) throws Exception {
		Map<String,Object> returnMap=commonRepository.verifyUserOnline(userName, password);
		WebResponseJsonBo resBo=new WebResponseJsonBo();
		resBo.setJsonListData(returnMap.get("responseData"));
		resBo.setReturn_message(returnMap.get("returnMessage").toString());
		resBo.setServiceStatus((boolean)returnMap.get("serviceStatus"));
		resBo.setValidated((boolean)returnMap.get("validated"));
		resBo.setStatus((int)returnMap.get("status"));
		return resBo;
	}
}
