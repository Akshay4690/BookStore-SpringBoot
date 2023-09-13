package com.BookStore.service;

import java.util.List;

import com.BookStore.dto.BookModelDTO;
import com.BookStore.entity.BookModel;

public interface IBookService {

	BookModel insertBook(BookModelDTO model);

	List<BookModel> getAllBooks();

	BookModel BooksGetById(int id);

	String bookDeleteById(int id);

	BookModel searchBookByName(String bookName);

	List<BookModel> sortBookByAsce();

	List<BookModel> sortBookByDesc();

	BookModel updateBookById(int id, BookModelDTO model);

	BookModel updateBookByQuantity(int quantity, int id);
	
	

}
