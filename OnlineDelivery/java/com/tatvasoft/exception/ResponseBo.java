package com.tatvasoft.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Setter;

import lombok.Getter;

@Getter
@Setter
public class ResponseBo {

	@JsonProperty(value = "status")
	private String status;
	
	@JsonProperty(value = "is_validated")
	private boolean isValidated;
	
	@JsonProperty(value="return_message")
	private String returnMessage;
	
	@JsonProperty(value="return_data")
	private Object returnData;
	
}
