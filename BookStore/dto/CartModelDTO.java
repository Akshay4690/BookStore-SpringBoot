package com.BookStore.dto;

import io.micrometer.common.lang.NonNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartModelDTO {
	
	@NonNull
	private int userId;
	private int bookId;
	private int quantity;
	
}
