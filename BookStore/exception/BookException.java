package com.BookStore.exception;

public class BookException extends RuntimeException {
	
private String message;
	
	public BookException (String message) {
		super(message);
	}

}
