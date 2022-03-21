package com.tatvasoft.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tatvasoft.commonentity.WebResponseJsonBo;
import com.tatvasoft.entity.MstUserRegBo;
import com.tatvasoft.jaspersec.GenerateJasper;
import com.tatvasoft.service.OnlineService;

@RestController
@RequestMapping("/delivery")
@CrossOrigin("*")
public class OnlineDeliveryController {

	@Autowired
	OnlineService onlineService;
	
	@PostMapping("/getUserData/{userId}/{jasperName}")
	public void getUserData(@PathVariable(name = "userId")int userId,@PathVariable("jasperName")String jasperName ,HttpServletRequest req,HttpServletResponse res)throws Exception
	{
		Map<String,Object> map=new HashMap<>();
		map.put("userId", userId);
		WebResponseJsonBo resObj=onlineService.getUserData(map);

		if (jasperName != null && !"".equals(jasperName)) {
			
			 GenerateJasper generateJasperObj1=new GenerateJasper();
			 ArrayList<MstUserRegBo> ls=new ArrayList<MstUserRegBo>();
			 MstUserRegBo regBo=(MstUserRegBo)resObj.getJsonListData();
			 ls.add(regBo);
			 byte[] pdfFile = generateJasperObj1.generatePDF(req,jasperName,ls); 	
			 
			 res.setContentType("application/pdf");
			 res.setHeader("Content-Disposition", "attachment;filename=\""+jasperName+".pdf\"");     
			 res.setContentLength(pdfFile.length);
	         ServletOutputStream out = res.getOutputStream();
	         out.write(pdfFile);
	         out.flush();
	         out.close();
		}else {
			PrintWriter printWriter = res.getWriter();
			printWriter.print("<html><script>alert('No Data found');window.close()</script></html>");
		}
	}
	
	@RequestMapping("/TestUrl")
	public WebResponseJsonBo testUrl()
	{
		return null;
	}
	
}
