package com.BookStore.entity;

import java.util.Date;

import com.BookStore.dto.OrderModelDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class OrderModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int orderId;
	
	private Date LocalDate;
	private int totalPrice;
	private int quantity;
	private String address;
	private int userId;
	private int bookId;
	private boolean cancel;
	
	public OrderModel(OrderModelDTO model) {
		
		this.LocalDate = model.getLocalDate();
		this.totalPrice = model.getTotalPrice();
		this.quantity = model.getQuantity();
		this.address = model.getAddress();
		this.userId = model.getUserId();
		this.bookId = model.getBookId();
		
	}
	
}
