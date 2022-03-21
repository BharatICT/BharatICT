package com.tatvasoft.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@MappedSuperclass
public class CommonTableFieldBo{

	@Column(name = "CRT_DATE",updatable = false)
	private Date crtDate;
	
	@Column(name = "CRT_USER",updatable = false)
	private String crtUser;
	
	@Column(name = "CRT_IP",updatable = false)
	private String crIp;
	
	@Column(name = "LST_UPD_DATE")
	private Date lstUpdDate;
	
	@Column(name = "LST_UPD_USER")
	private String lstUpdUser;
	
	@Column(name = "LST_UPD_IP")
	private String lstUpdIp;
	
	@Column(name = "STATUS",updatable = false)
	private String status;
	
	
}
