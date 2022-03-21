package com.tatvasoft.commonentity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthenticationRequest {

	@NotNull
	@NotEmpty(message = "username not be empty")
	private String username;
	
	private String password;
	
	private String language;
	
	@NotNull
	@NotEmpty(message = "password not be empty")
	private String encripted_password;
}
