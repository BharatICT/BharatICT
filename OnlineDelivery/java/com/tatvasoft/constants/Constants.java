package com.tatvasoft.constants;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Baghel Mit
 * for constants
 */
public class Constants
{
	public static final Map<String,Object> APPSERVICECODE=new HashMap<String, Object>();
	public static final Map<String,Object> APPSUBSERVICECODE=new HashMap<String, Object>();
	
	public static String userId="";
	public static String mobileno="";
	public static String loginId="";
	private Constants()
	{
		
	}
	public static final String PROJECT_TITLE="PANCHANTANTRA";
	public static final int SUCCESS_CODE=1000;
	public static final int ERROR_CODE=999;
	public static final String INSERT="INSERT";
	public static final String UPDATE="UPDATE";
	public static final String DELETE="DELETE";
	public static final String YES_STATUS="YES";
	public static final String NO_STATUS="NO";
	public static final String STATUS_1="1";
	public static final String STATUS_2="2";
	public static final String MSG_ERROR="ERROR";
	public static final String MSG_SUCCESS="SUCCESS";
	public static final String STATUS_NO_VALUE="";
	
	// Start  :: Mitesh Chaudhari  :: //
	public static final String SIMPLE_TEXT="^[a-zA-Z]*$";
	public static final String SIMPLE_TEXT_WITH_SPLACE="^[a-zA-Z ]*$";
	public static final String SIMPLE_TEXT_WITH_SINGLE_SPLACE="^([a-zA-Z]+ )*[a-zA-Z]*$";
	public static final String SIMPLE_TEXT_WITH_COMMA="^[a-zA-Z,]*$";
	public static final String SIMPLE_TEXT_WITH_COMMA_SPACE="^([a-zA-Z,]+ )*[a-zA-Z,]*$";
	public static final String SIMPLE_TEXT_WITH_COMMA_SPACE_SPECIAL="^([a-zA-Z0-9,\\-.()\\'\\/]+ )*[a-zA-Z0-9,\\-.()\\'\\/]*$";
	public static final String TEXT_WITH_SPECIAL_CHAR="^([a-zA-Z\\-.()&]+ )*([a-zA-Z\\-.()&])*$";
	
	public static final String ALPHANUMRIC_UPPER_TEXT="^[A-Z0-9]*$";
	public static final String ALPHANUMRIC_TEXT="^[a-zA-Z0-9]*$";
	public static final String ALPHANUMRIC_TEXT_WITH_COMMA="^[a-zA-Z0-9,]*$";
	public static final String ALPHANUMRIC_TEXT_WITH_COMMA_SPACE="^([a-zA-Z0-9,]+ )*[a-zA-Z0-9,]*$";
	public static final String ALPHANUMRIC_TEXT_WITH_COMMA_SPACE_SPEICAL="^([a-zA-Z0-9,\\-\\'\\/]+ )*[a-zA-Z0-9,\\-\\'\\/]*$";
	
	public static final String ONLY_DIGIT="[0-9]*$";
	public static final String ONLY_DIGIT_WITH_DOT="[0-9]*(.){1}[0-9]*$";
	public static final String YEAR_VALIDATE="^([0-9]{4})$";
	public static final String AADHAR_NO="^([0-9]{12})$";
	public static final String MOBILE_NO="^(((6|7|8|9))[0-9]{9})$";
	public static final String MOBILE_NO_WITH_NULL="^(((6|7|8|9))[0-9]{9})*$";
	public static final String EMAIL_ID="^([a-zA-Z]{1}[\\w\\-\\.]*\\@([\\da-zA-Z\\-]{1,}\\.){1,}[\\da-zA-Z\\-]{2,4})$";
	public static final String EMAIL_ID_WITH_NULL="^([a-zA-Z]{1}[\\w\\-\\.]*\\@([\\da-zA-Z\\-]{1,}\\.){1,}[\\da-zA-Z\\-]{2,4})*$";
	public static final String PINCODE="([0-9]{6})*$";
	public static final String PERCENTAGE_WITH_NUMBER="(^100(\\.0{1,2})?$)|(^([4-8]([0-9])?|9[0-9])(\\.[0-9]{1,2})?$)";
	public static final String CHECK_BLOOD_GROUP="^[A|B|O]{1,2}[+,-]$";
	public static final String CHECK_URL="((http|https)://)(www.)?[a-zA-Z0-9@:%._\\+~#?&//=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%._\\+~#?&//=]*)";
	public static final String CHECK_POSTAL_CODE="^[0-9]{5}(?:-[0-9]{4})?$";
	public static final String CHECK_POSTAL_CODE_NULL="^([0-9]{5}(?:-[0-9]{4})?)*$";
	public static final String PHONE_NO_WITH_NULL="^((\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$)*" 
												+ "|^((\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$)*" 
												+ "|^((\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$)*";
	public static final String FAX_NO_WITH_NULL="^(\\+(91)[- ]{1}[0-9]{1,4}[- ]{1}[0-9]{6,8})*$";
			
	
	public static final String DATE_FORMAT_PATTERN_DASH_WITH_NULL  = "^((([0-9])|([0-2][0-9])|([3][0-1]))\\-(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)\\-\\d{4})*$";
	public static final String CHECK_ACA_YEAR="^[2][0-9]{3}-[2][0-9]{1,3}$";
	public static final String CHECK_ACA_YEAR_NULL="^([2][0-9]{3}-[2][0-9]{3})*$";
	
	public static final String ONE_OR_TWO= "1|2";
	
	public static final String SIMPLE_TEXT_MSG="Please enter only alphabets";
	public static final String SIMPLE_TEXT_WITH_SPLACE_MSG="Please enter only alphabets with single space";
	public static final String ALPHANUMRIC_TEXT_MSG="Please enter alpha numeric";
	public static final String ALPHANUMRIC_TEXT_WITH_SPACE_MSG="Please enter alpha numeric with single space";
	
	
	public static final String ONLY_DIGIT_MSG="Please enter only digits";
	public static final String MOBILE_NO_MSG="Please enter valid mobile number";
	public static final String EMAIL_ID_MSG="Please enter valid email id";
	public static final String PINCODE_MSG="Please enter valid pincode";
	public static final String PERCENTAGE_MSG="Please enter valid percentage";
	public static final String AADHAR_MSG="Please enter valid aadhar number";
	public static final String BLOOD_GROUP_MSG="Please enter valid blood group";
	public static final String URL_MSG="Please enter valid URL";
	public static final String POSTAL_CODE_MSG="Please enter valid postal code";
	public static final String PHONE_NO_MSG="Please enter valid phone number";
	public static final String FAX_NO_MSG="Please enter valid fax number";
	public static final String ACA_YEAR_MSG="Please enter valid academic year";
	public static final String DATE_FORMATE_MSG="Please enter date dd-Mon-YYYY formate";
	public static final String TEXT_WITH_SPECIAL_CHAR_MSG="Please enter alphabets with only (-,.,(),&) characters are allowed";
	// End  :: Mitesh Chaudhari  :: //
	
	//Start :: Dangi Ashish :: //
	public static final String SIMPLE_KANNADA_TEXT_WITH_SPLACE="^[\u00A1-\uFFFF0-9 ]*$";
	//End :: Dangi Ashish :: //
	//LALJI LILAPARA
	
	public static final String Active="1";
	public static final String DeActive="0";
	
	//LALJI LILAPARA
	public static final String ACTIVATE="ACTIVATE";
	public static final String DEACTIVATE="DEACTIVATE";
	
	public static int count=0;
	
	/**START :: BAGHEL MITKUMAR **/

	public static final String CURR_YEAR="2021";
	/**START :: BAGHEL MITKUMAR **/
	
	
	
	
}



