package com.tatvasoft.jaspersec;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;



public class CommonUtility 
{
	public static String jasper_Path;
	public static String label_Path;
	private static final String OS_TYPE_LINUX = "Linux";
	private static final String OS_TYPE_WINDOWS = "Windows";
	private static final String OS_TYPE_OTHER = "Other";
	public static String senderid;
	
	
	public static String getJasperPath(HttpServletRequest request) throws IOException
	{
		String version = System.getProperty("java.version");
		String path;
		if (CommonUtility.getOsName().equals("Linux")) 
		{
			path =	 request.getRealPath("//")+"resources//jasper//";
		}
		else if (CommonUtility.getOsName().equals("Windows")) 
		{
			path =	request.getRealPath("\\")+"/resources/jasper/";
			if(version.contains("11"))
			{
				path =	 request.getRealPath("\\")+"/resources/jasper/";
			}
			else
			{
				path =	request.getRealPath("\\")+"/resources/jasper/";
			}
		}
		else
		{
			path =	request+"resources/jasper/";
			if(version.contains("11"))
			{
				path =	request+"/resources/jasper/";
			}
			else
			{
				path =	request+"resources/jasper/";
			}
		}
		CommonUtility.jasper_Path=path;
		return path;
	}
	
	public static String getOsName() 
	{
		String osType = null;
		osType = System.getProperty("os.name");
		if(osType.contains("Windows"))
		{
			osType = OS_TYPE_WINDOWS;
		}
		else if(osType.contains("nux") || osType.contains("nix"))
		{
			osType = OS_TYPE_LINUX ;
		}
		else
		{
			osType = OS_TYPE_OTHER;
		}
		return osType;
	}
	
	public static boolean checkString(String s)
	{
		boolean bError=true;
		if(s != null &&!("".equals(s)))
		{
			bError=false;
		}
		return bError; 
	}
	
	public static String getAnyFilePath(String folderName)
	{
		String filePath = "";
		ResourceBundle rb = ResourceBundle.getBundle("Config.CommonConfig");
		String osName = CommonUtility.getOsName();	
	
			if ("Linux".equalsIgnoreCase(osName)) 
			{
				filePath = rb.getString(folderName+".linux");
			}
			else 
			{
				filePath = rb.getString(folderName+".windows");
			}
			File f=new File(filePath);
			if(!f.exists())
			{
				f.mkdirs();
			}
		return filePath;
	}
	
	public static String getAnyFilePathWithSubFolder(String folderName,String subFolderName)
	{
		String filePath = "";
		ResourceBundle rb = ResourceBundle.getBundle("Config.CommonConfig");
		String osName = CommonUtility.getOsName();
	
			if ("Linux".equalsIgnoreCase(osName)) 
			{
				filePath = rb.getString(folderName+".linux");
				filePath+="/"+subFolderName+"/";
			}
			else 
			{
				filePath = rb.getString(folderName+".windows");
				filePath+=subFolderName+"\\";
			}
			File f=new File(filePath);
			if(!f.exists())
			{
				f.mkdirs();
			}
			return filePath;
	}
	
	public static String getImageByteString(String imagePath) throws Exception 
	{
		File f = new File(imagePath);
		if(f.exists())
		{
			BufferedImage bImage = ImageIO.read(f);// give the path of photo
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(bImage, "jpg", baos);
			baos.flush();
			byte[] imageInByteArray = baos.toByteArray();
			baos.close();
	
			byte[] bytesEncoded = Base64.getEncoder().encode(imageInByteArray);
			//System.out.println("encoded value is " + new String(bytesEncoded));
		       return new String(bytesEncoded);
		}
			return null;
	 }
	
	
	public static Map<String, Object> uploadPdfFileMultipart(MultipartFile multiPartFile, String folderName) 
	{

		boolean flag = false;
		Map<String, Object> map = new HashMap<>();
		String fileName =System.currentTimeMillis()+multiPartFile.getOriginalFilename()+getExtension(multiPartFile.getOriginalFilename());
		String filePath = getAnyFilePath(folderName);
		try 
		{
			multiPartFile.transferTo(new File(filePath+fileName));
			File f = new File(filePath + fileName);
			if (f.exists() && f.length() > 0) 
			{
				map.put("filePath", filePath + fileName);
				map.put("fileName", fileName);
				flag = true;
			}
			map.put("flag", flag);
		} 
		catch (IllegalStateException | IOException e) 
		{
			e.printStackTrace();
		}
		return map;
	}
	
	
	public static Map<String, Object> uploadMultipartData(MultipartFile multiPartFile, String folderName,String user_id) {

		boolean flag = false;
		Map<String, Object> map = new HashMap<>();
		String fileName = multiPartFile.getOriginalFilename();//+getExtension(multiPartFile.getOriginalFilename())
		String filePath = getAnyFilePathWithSubFolder(folderName,user_id);
		try {
			multiPartFile.transferTo(new File(filePath+fileName));
			File f = new File(filePath + fileName);
			if (f.exists() && f.length() > 0) {
				map.put("filePath", filePath + fileName);
				map.put("fileName", fileName);
				map.put("path", filePath);
				flag = true;
			}
			map.put("flag", flag);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		return map;
	}
	public static String getExtension(String fileExtension)
	{
		String extension="";
		if(fileExtension != null && !fileExtension.isEmpty() && !"".equalsIgnoreCase(fileExtension) )
		{
			extension=fileExtension.substring(fileExtension.lastIndexOf("."));
		}
		return extension;
	}
	
	
	@SuppressWarnings("unused")
	public static String getJaxbXMLPath(ServletContext servletcontext) throws IOException
	{
		String version = System.getProperty("java.version");
		String path;
		if (CommonUtility.getOsName().equals("Linux")) 
		{
			path =	 servletcontext.getRealPath("//")+"resources//ROLEXML//";
		}
		else if (CommonUtility.getOsName().equals("Windows")) 
		{
			path =	servletcontext.getRealPath("\\")+"resources/ROLEXML/";
			if(version.contains("1.8"))
			{
				path =	 servletcontext.getRealPath("\\")+"/resources/ROLEXML/";
			}
			else
			{
				path =	servletcontext.getRealPath("\\")+"resources/ROLEXML/";
			}
		}
		else
		{
			path =	servletcontext+"resources/ROLEXML/";
			if(version.contains("1.8"))
			{
				path =	servletcontext+"/resources/ROLEXML/";
			}
			else
			{
				path =	servletcontext+"resources/ROLEXML/";
			}
		}
		return path;
	}
	
	@SuppressWarnings("unused")
	public static String getJaxbXMLPath(HttpServletRequest request) throws IOException
	{
		String version = System.getProperty("java.version");
		String path;
		if (CommonUtility.getOsName().equals("Linux")) 
		{
			path =	 request.getRealPath("//")+"resources//ROLEXML//";
		}
		else if (CommonUtility.getOsName().equals("Windows")) 
		{
			path =	request.getRealPath("\\")+"resources/ROLEXML/";
			if(version.contains("1.8"))
			{
				path =	 request.getRealPath("\\")+"/resources/ROLEXML/";
			}
			else
			{
				path =	request.getRealPath("\\")+"resources/ROLEXML/";
			}
		}
		else
		{
			path =	request+"resources/ROLEXML/";
			if(version.contains("1.8"))
			{
				path =	request+"/resources/ROLEXML/";
			}
			else
			{
				path =	request+"resources/ROLEXML/";
			}
		}
		return path;
	}
	
	public static byte[] readFileToByteArray(File file)
	{
        FileInputStream fis = null;
        // Creating a byte array using the length of the file
        // file.length returns long which is cast to int
        byte[] bArray = new byte[(int) file.length()];
        try
        {
            fis = new FileInputStream(file);
            fis.read(bArray);
            fis.close();        
        }
        catch(IOException ioExp)
        {
            ioExp.printStackTrace();
        }
        return bArray;
    }
	
	public static String getIp(HttpServletRequest request) throws UnknownHostException
	{
		String usrIp = request.getHeader("X-FORWARDED-FOR");
		if(usrIp==null || usrIp == "")
		{
			usrIp = request.getRemoteAddr();
		}
		
		return usrIp;
	}
	
	//BAGHEL MITKUMAR
	public static Map<String, Object> uploadMultipartDataFile(MultipartFile multiPartFile, String folderName,String fileNameFromBO) {

		boolean flag = false;
		Map<String, Object> map = new HashMap<>();
		String filePath = getAnyFilePath(folderName);
		
		try {
			multiPartFile.transferTo(new File(filePath+fileNameFromBO));
			File f = new File(filePath + fileNameFromBO);
			if (f.exists() && f.length() > 0) {
				map.put("filePath", filePath + fileNameFromBO);
				map.put("fileName", fileNameFromBO);
				map.put("path", filePath);
				flag = true;
			}
			map.put("flag", flag);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	public static String getRandomCharsWithEncrypted() throws Exception {
		String randomStr = "";
		String unencryptedString="";
		String encryptedString="";
		String possibleChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		Random rm=new Random();
//		CipherEncryptDecrypt ed=new CipherEncryptDecrypt();
		
		int otp=100+rm.nextInt(999);		
		for (int i = 0; i < 3; i++)
			randomStr += possibleChars.charAt((int) Math.floor(Math.random() * possibleChars.length()));
		
		unencryptedString=randomStr+Integer.toString(otp);
//		encryptedString=ed.encrypt(unencryptedString);
		
		return encryptedString;
	}
	

	/* Changes By Neel Shah Start */
	
	public static String getJasperPath(HttpServletRequest request, String folder_name) throws IOException
	{
		String version = System.getProperty("java.version");
		String path;
		if (CommonUtility.getOsName().equals("Linux")) 
		{
			System.out.println(request.getRealPath("//"));
			path =	 request.getRealPath("//")+"resources//jasper//"+folder_name+"//";
		}
		else if (CommonUtility.getOsName().equals("Windows")) 
		{
			path =	request.getRealPath("\\")+"/resources/jasper/"+folder_name+"/";
			if(version.contains("11"))
			{
				path =	 request.getRealPath("\\")+"/resources/jasper/"+folder_name+"/";
			}
			else
			{
				path =	request.getRealPath("\\")+"/resources/jasper/"+folder_name+"/";
			}
		}
		else
		{
			path =	request+"/resources/jasper/"+folder_name+"/";
			if(version.contains("11"))
			{
				path =	request+"/resources/jasper/"+folder_name+"/";
			}
			else
			{
				path =	request+"/resources/jasper/"+folder_name+"/";
			}
		}
		return path;
	}
	/* Changes By Neel Shah End */
	
	//Lalji Lilapara
	
	
	public static Map<String,String> getDistrict(){
  		Map<String,String> map = new HashMap<String,String>();
  		map.put("1","UDUPI");
  		map.put("2","BAGALKOTE");
  		return map;
  	}
	
	public static Map<String,String> getTaluka1(){
  		Map<String,String> map = new HashMap<String,String>();
  		map.put("1","BADAMI");
  		map.put("2","JAMKHANDI");
  		return map;
  	}
	
	public static Map<String,String> getTaluka2(){
  		Map<String,String> map = new HashMap<String,String>();
  		map.put("1","KARKALA");
  		map.put("2","KUNDAPURA");
  		return map;
  	}
	
	public static Map<String,String> getVillage1(){
  		Map<String,String> map = new HashMap<String,String>();
  		map.put("1","SANDUR");
  		map.put("2","KUDLIGI");
  		return map;
  	}
	
	public static Map<String,String> getVillage2(){
  		Map<String,String> map = new HashMap<String,String>();
  		map.put("1","BHALKI");
  		map.put("2","BIDAR");
  		return map;
  	}
	
	public static Map<String,String> getGp(){
  		Map<String,String> map = new HashMap<String,String>();
  		map.put("1","ADAGALL");
  		map.put("2","KAKANUR");
  		return map;
  	}
	
	//Lalji Lilapara

	public static final String[] units = { "", "One", "Two", "Three", "Four",
			"Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve",
			"Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen",
			"Eighteen", "Nineteen" };

			public static final String[] tens = { 
			        "",         // 0
			        "",     // 1
			        "Twenty",   // 2
			        "Thirty",   // 3
			        "Forty",    // 4
			        "Fifty",    // 5
			        "Sixty",    // 6
			        "Seventy",  // 7
			        "Eighty",   // 8
			        "Ninety"    // 9
			};
	public static String convert(final int n) {
	    if (n < 0) {
	        return "Minus " + convert(-n);
	    }

	    if (n < 20) {
	        return units[n];
	    }

	    if (n < 100) {
	        return tens[n / 10] + ((n % 10 != 0) ? " " : "") + units[n % 10];
	    }

	    if (n < 1000) {
	        return units[n / 100] + " Hundred Only." + ((n % 100 != 0) ? " " : "") + convert(n % 100);
	    }

	    if (n < 100000) {
	        return convert(n / 1000) + " Thousand Only." + ((n % 10000 != 0) ? " " : "") + convert(n % 1000);
	    }

	    if (n < 10000000) {
	        return convert(n / 100000) + " Lakh Only." + ((n % 100000 != 0) ? " " : "") + convert(n % 100000);
	    }

	    return convert(n / 10000000) + " Crore Only." + ((n % 10000000 != 0) ? " " : "") + convert(n % 10000000);
	}
}
