package com.tatvasoft.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Transient;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name="MST_USER_REG")
public class MstUserRegBo extends CommonTableFieldBo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="USER_ID")
	private Integer userId;
	
	@NotBlank(message = "Please enter firstName")
	@Column(name="FIRST_NAME")
	private String firstName;
	
	@NotBlank(message = "Please enter lastName")
	@Column(name="LAST_NAME")
	private String lastName;
	
	@NotBlank(message = "Please enter middleName")
	@Column(name="MIDDLE_NAME")
	private String middleName;
	
	@Column(name="LOGIN_NAME")
	private String loginName;
	
	@Column(name="LOGIN_PASSWORD")
	private String loginPassword;
	
	@Column(name="EMAIL_ID")
	private String emailId;
	
	@Column(name="MOBILE_NO")
	private String mobileNo;
	
	@Column(name="CONTACT_NO")
	private String contactNo;
	
	@Column(name="ROLE_ID")
	private String roleId;
	
	@Transient
	private String password;
}
