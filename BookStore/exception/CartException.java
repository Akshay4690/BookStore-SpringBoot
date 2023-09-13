package com.BookStore.exception;

public class CartException extends RuntimeException {
	
	
private String message;
	
	public CartException (String message) {
		super(message);
	}
}
