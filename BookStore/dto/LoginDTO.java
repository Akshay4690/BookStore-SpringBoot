package com.BookStore.dto;

import com.BookStore.entity.UserModel;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginDTO {
	
	private String email;
	private String password;
	
	public LoginDTO(String email,String password)
	{
		this.email=email;
		this.password=password;
	}

}
