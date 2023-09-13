package com.BookStore.entity;

import com.BookStore.dto.BookModelDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class BookModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int bookId;
	private String bookName;
	private String autherName;
	private String bookImg ; 
	private int price;
	private int quantity;

	public BookModel(BookModelDTO model) {
		
		this.bookName = model.getBookName();
		this.autherName = model.getAutherName();
		this.bookImg = model.getBookImg();
		this.price = model.getPrice();
		this.quantity = model.getQuantity();
	}

}
