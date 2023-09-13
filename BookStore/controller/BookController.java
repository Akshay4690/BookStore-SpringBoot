package com.BookStore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.BookStore.dto.BookModelDTO;
import com.BookStore.dto.responseDTO;
import com.BookStore.entity.BookModel;
import com.BookStore.service.IBookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	IBookService service;
	
	// Insert the Books 
	@PostMapping("/insert")
	public ResponseEntity<responseDTO> insert (@Valid @RequestBody BookModelDTO model)
	{
		BookModel details = service.insertBook(model);
		responseDTO response = new responseDTO(details,"Book Insert Successfully !");
		return new ResponseEntity<responseDTO> (response,HttpStatus.CREATED);
	}
	
	// Get All Books
	@GetMapping("/getAll")
	public ResponseEntity<responseDTO> getAll ()
	{
		List<BookModel> details = service.getAllBooks();
		responseDTO response = new responseDTO(details,"Get All Book Successfully !");
		return new ResponseEntity<responseDTO> (response,HttpStatus.CREATED);	
	}
	
	// Get Book By Id
	@GetMapping("/getById/{id}")
	public ResponseEntity<responseDTO> getById (@PathVariable int id)
	{
		BookModel details = service.BooksGetById(id);
		responseDTO response = new responseDTO(details,"Books getting by id Successfully !");
		return new ResponseEntity<responseDTO> (response,HttpStatus.OK);	
	}
	
	// Delete Book By Id
	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<responseDTO> deleteById(@PathVariable int id)
	{
		String details = service.bookDeleteById(id);
		responseDTO response = new responseDTO(details,"Books Delete by id successfully");
		return new ResponseEntity<responseDTO> (response,HttpStatus.OK);
	}
	
	// Search Books By Name
	@GetMapping("/searchByBookName/{bookName}")
	public ResponseEntity<responseDTO> searchByName(@PathVariable String bookName,@Valid @RequestBody BookModelDTO model)
	{
		BookModel details = service.searchBookByName(bookName);
		responseDTO response = new responseDTO(details,"Books getting by id Successfully !");
		return new ResponseEntity<responseDTO> (response,HttpStatus.OK);
	}
	
	// Update Book By Id
	@PutMapping("/updateBookById/{id}")
	public ResponseEntity<responseDTO> updateById(@PathVariable int id,@Valid @RequestBody BookModelDTO model)
	{
		BookModel details = service.updateBookById(id,model);
		responseDTO response = new responseDTO(details,"Book Updated Successfully !");
		return new ResponseEntity<responseDTO> (response,HttpStatus.ACCEPTED);
	}
	
	// Sorting Book Ascending Order
	@GetMapping("/sortingAsce")
	public ResponseEntity<responseDTO> sortAsce() {
		
		List<BookModel> details = service.sortBookByAsce();
		responseDTO response = new responseDTO(details,"Books sorting by ascending order Successfully !");
		return new ResponseEntity<responseDTO> (response,HttpStatus.OK);
	}
	
	// Sorting Book Descending order
	@GetMapping("/sortingDesc")
	public ResponseEntity<responseDTO> sortDesc() 
	{
		List<BookModel> details = service.sortBookByDesc();
		responseDTO response = new responseDTO(details,"Books sorting by descending order Successfully !");
		return new ResponseEntity<responseDTO> (response,HttpStatus.OK);
	}
	
	// Update Books by quantity
	@PutMapping("/updateQuantity/{id}")
	public ResponseEntity<responseDTO> updateByQuantity(@PathVariable int quantity,@RequestParam int id)
	{
		BookModel details = service.updateBookByQuantity(quantity,id);
		responseDTO response = new responseDTO(details,"Book Updated By Quantity Successfully !");
		return new ResponseEntity<responseDTO> (response,HttpStatus.ACCEPTED);
	}

}
