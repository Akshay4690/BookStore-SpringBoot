package com.BookStore.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class responseDTO {
	
	private String message ;
	private Object data;
	
	public responseDTO(Object data, String message)
	{
		this.message = message;
		this.data = data ;
	}

}
