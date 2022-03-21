package com.tatvasoft.serviceimpl;

import java.util.List;
import java.util.Map;

import javax.persistence.Tuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tatvasoft.commonentity.WebResponseJsonBo;
import com.tatvasoft.commonrepo.CommonRepository;
import com.tatvasoft.constants.Constants;
import com.tatvasoft.entity.MstUserRegBo;
import com.tatvasoft.exception.ResourceNotFoundException;
import com.tatvasoft.service.OnlineService;

@Service
public class OnlineServiceImpl implements OnlineService{

	java.util.function.Predicate<String> stringCheck= s -> s != null && s.length() != 0;
	java.util.function.Predicate<Integer> intCheck= s -> s != null && s.intValue() != 0;
	java.util.function.Predicate<List<Tuple>> listTSizeCheck= list -> list != null && list.size() > 0; 
	
	@Autowired
	CommonRepository commonRepo;
	
	@Override
	public WebResponseJsonBo getUserData(Map<String, Object> map) throws Exception {
		WebResponseJsonBo resObj=new WebResponseJsonBo();
		
		int userId=(int)map.get("userId");
		if(intCheck.test(userId))
		{
			MstUserRegBo userBo=commonRepo.getUserData(userId);
			if(userBo!=null)
			{
				resObj.setValidated(true);
				resObj.setJsonListData(userBo);
				resObj.setStatus(Constants.SUCCESS_CODE);
			}
			else
			{
				resObj.setValidated(false);
				resObj.setStatus(Constants.ERROR_CODE);
				resObj.setReturn_message("Data not found");
			}
		}
		else
		{
			throw new ResourceNotFoundException("Please enter userId");
		}
		return resObj;
	}

	
}
