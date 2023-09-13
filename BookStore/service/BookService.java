package com.BookStore.service;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.BookStore.dto.BookModelDTO;
import com.BookStore.entity.BookModel;
import com.BookStore.exception.BookException;
import com.BookStore.repository.BookRepository;
import com.BookStore.util.BookTokenUtil;
import com.BookStore.util.SendEmailService;

@Service
public class BookService implements IBookService{

	@Autowired
	BookRepository repository;
	
	@Autowired
	BookTokenUtil tokenUtil;
	
	@Autowired
	SendEmailService emailsender;
	
	@Autowired
	ModelMapper modelMapper;

	// Insert the Books 
	@Override
	public BookModel insertBook(BookModelDTO model) {
		
		BookModel user = new BookModel(model);
		repository.save(user);
		
		String token = tokenUtil.createToken(user.getBookId());
		System.out.println(token);
		return user;
	}

	// Get All Books
	@Override
	public List<BookModel> getAllBooks() {
		return repository.findAll();
	}

	// Get Book By Id
	@Override
	public BookModel BooksGetById(int id) {
		
		Optional<BookModel> user = repository.findById(id);
		if (user.isPresent()) {
			return user.get();
		}
		else throw new BookException("Book Not Found");
	}

	// Delete Book By Id
	@Override
	public String bookDeleteById(int id) {
		
		Optional<BookModel> user = repository.findById(id);
		
		if(user.isPresent()) {
			repository.deleteById(id);
			return "User deleted Successfully,for id" +id;
		}
		else throw new BookException("Book Not Found");
	}

	// Search Books By Name
	@Override
	public BookModel searchBookByName(String bookName) {
		
		BookModel user = repository.findByBookName(bookName);
		return user;
	}

	// Sorting Book Ascending order
	@Override
	public List<BookModel> sortBookByAsce() 
	{	
		return repository.findAll(Sort.by(Sort.Direction.ASC,"bookId"));
	}

	// Sorting Book Descending order
	@Override
	public List<BookModel> sortBookByDesc() {
		
		return repository.findAll(Sort.by(Sort.Direction.DESC,"bookId"));
	}

	// Update Book By Id
	@Override
	public BookModel updateBookById(int id, BookModelDTO model) {
		
	    Optional<BookModel> book = repository.findById(id);
	    if(book.isPresent())
	    {
	    	
	    	BookModel book1 = modelMapper.map(model,BookModel.class);
	    	book1.setBookId(id);
	    //	book= Optional.of(new BookModel(book.get().getBookId(),model));
	    	repository.save(book1);
	    	return book1 ;
	    }
	    else throw new BookException("Book Not Found !");
	}

	// Update quantity by id
	@Override
	public BookModel updateBookByQuantity(int quantity, int id) {
		Optional<BookModel> user = repository.findById(id);
		
		if(user.isPresent())
		{
			user.get().setQuantity(quantity);
			repository.save(user.get());
			return user.get();
		}
		else throw new BookException("Book Not Found By Quantity");
	}
}
