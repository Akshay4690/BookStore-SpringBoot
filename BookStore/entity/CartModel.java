package com.BookStore.entity;

import com.BookStore.dto.CartModelDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class CartModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cartId;
	private int userId;
	private int bookId;
	private int quantity;

	public CartModel(CartModelDTO model) {
		
		this.userId = model.getUserId();
		this.bookId = model.getBookId();
		this.quantity = model.getQuantity();
	}
}
