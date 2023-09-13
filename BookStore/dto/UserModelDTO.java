package com.BookStore.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserModelDTO {
	
	@Pattern (regexp="^[A-Z]{1}[a-zA-Z]{2,}$", message = "Naming convention should as per condition")
	private String firstName;
	private String lastName;
	
	@Pattern(regexp="[A-Za-z0-9+_.]+@(.+)$", message = "Format of the email as per validation")
	private String email;
	private String address;
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private Date dob;
	private String password;
	

}
