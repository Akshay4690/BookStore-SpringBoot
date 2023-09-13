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

import com.BookStore.dto.OrderModelDTO;
import com.BookStore.dto.responseDTO;
import com.BookStore.entity.OrderModel;
import com.BookStore.service.IOrderService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	IOrderService service;
	
	// Insert The Order
	@PostMapping("/insert")
	public ResponseEntity<responseDTO> insert ( @RequestBody OrderModelDTO model)
	{
		OrderModel details = service.InsertOrder(model);
		responseDTO response = new responseDTO(details,"Order Inserted Successfully !");
		return new ResponseEntity<responseDTO> (response,HttpStatus.ACCEPTED);
	}
	
	// Get All Orders
	@GetMapping("/getAll")
	public ResponseEntity<responseDTO> getAll ()
	{
		List<OrderModel> details = service.allOrders();
		responseDTO response = new responseDTO(details,"Get All Order Successfully !");
		return new ResponseEntity<responseDTO> (response,HttpStatus.OK);
	}
	
	// Orders get by id
	@GetMapping("/getById/{id}")
	public ResponseEntity<responseDTO> getById(@PathVariable int id)
	{
		OrderModel details = service.orderById(id);
		responseDTO response = new responseDTO(details,"Order Gets By Id Successfully !");
		return new ResponseEntity<responseDTO> (response,HttpStatus.ACCEPTED);
	}
	
	// Delete Order By Id
	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<responseDTO> deleteById(@PathVariable int id)
	{
		String details = service.deleteOrder(id);
		responseDTO response = new responseDTO(details,"Order Delete By Id Successfully !");
		return new ResponseEntity<responseDTO> (response,HttpStatus.ACCEPTED);
	}
	
	// Update Order By Id
	@PutMapping("/updateById/{id}")
	public ResponseEntity<responseDTO> updateOrderById (@PathVariable int id,@Valid @RequestBody OrderModelDTO model)
	{
		OrderModel details = service.updateOrder(id,model);
		responseDTO response = new responseDTO(details,"Order update successfully !");
		return new ResponseEntity<responseDTO> (response,HttpStatus.ACCEPTED);
	}
	
	// Order Cancle by id
	@GetMapping("/cancelOrder/{id}")
	public ResponseEntity<responseDTO> cancleOrderById(@PathVariable int id)
	{
		OrderModel details = service.cancleOrder(id);
		responseDTO response = new responseDTO(details,"Order Cancel successfully !");
		return new ResponseEntity<responseDTO> (response,HttpStatus.ACCEPTED);	
	}
}
