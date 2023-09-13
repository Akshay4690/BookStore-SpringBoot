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
import org.springframework.web.bind.annotation.RestController;

import com.BookStore.dto.CartModelDTO;
import com.BookStore.dto.responseDTO;
import com.BookStore.entity.CartModel;
import com.BookStore.service.ICartService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	ICartService service;
	
	// Insert the Cart
	@PostMapping("/insert")
	public ResponseEntity<responseDTO> cartInsert (@Valid @RequestBody CartModelDTO model ) 
	{
		CartModel details = service.insertCart(model);
		responseDTO response = new responseDTO(details,"Cart insert Successfully");
		return new ResponseEntity<responseDTO> (response,HttpStatus.CREATED);	
	}
	
	// Get All Carts
	@GetMapping("/getAll")
	public ResponseEntity<responseDTO> getAll() 
	{
		List<CartModel> details = service.getAllCart();
		responseDTO response = new responseDTO(details,"Get All Cart Successfully");
		return new ResponseEntity<responseDTO> (response,HttpStatus.OK);
	}
	
	// Get Cart By Id
	@GetMapping("/getById")
	public ResponseEntity<responseDTO> getById (@PathVariable int id)
	{
		CartModel details = service.CartById(id);
		responseDTO response = new responseDTO(details,"Get All Cart Successfully");
		return new ResponseEntity<responseDTO> (response,HttpStatus.OK);
	}

	// Delete Cart by id
	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<responseDTO> deleteById(@PathVariable int id)
	{
		String details = service.deleteCart(id);
		responseDTO response = new responseDTO(details," Cart delete Successfully");
		return new ResponseEntity<responseDTO> (response,HttpStatus.OK);
	}
	
	// Update Carts by id
	@PutMapping("/updateById/{id}")
	public ResponseEntity<responseDTO> updateId (@PathVariable int id,@Valid @RequestBody CartModelDTO model) 
	{
		CartModel details = service.CartUpdate(id,model);
		responseDTO response = new responseDTO(details," Cart update by id Successfully");
		return new ResponseEntity<responseDTO> (response,HttpStatus.OK);
	}
	
	// Update By Quantity
	@PutMapping("/updateByQuantity/{id}")
	public ResponseEntity<responseDTO> updateQuantityById (@PathVariable int id,@Valid @RequestBody CartModelDTO model)
	{
		CartModel details = service.UpdateQuantity(id,model);
		responseDTO response = new responseDTO(details," Quantity update by id Successfully");
		return new ResponseEntity<responseDTO> (response,HttpStatus.OK);
	}
}
