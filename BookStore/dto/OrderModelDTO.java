package com.BookStore.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderModelDTO {
	
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private Date LocalDate;
	@NotNull
	private int totalPrice;
	@NotEmpty
	private int quantity;
	private String address;
	
	private int userId;
	private int bookId;
	private boolean cancel;
	

}
