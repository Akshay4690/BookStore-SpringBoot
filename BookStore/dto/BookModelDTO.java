package com.BookStore.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookModelDTO {
	
	private String bookName;
	@Pattern (regexp="^[A-Z]{1}[a-zA-Z]{2,}$", message = "Naming convention should as per condition")
	private String autherName;
	private String bookImg ; 
	@NotNull
	private int price;
	private int quantity;

}
