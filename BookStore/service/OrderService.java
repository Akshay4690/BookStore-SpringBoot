package com.BookStore.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BookStore.dto.OrderModelDTO;
import com.BookStore.dto.UserModelDTO;
import com.BookStore.entity.BookModel;
import com.BookStore.entity.CartModel;
import com.BookStore.entity.OrderModel;
import com.BookStore.entity.UserModel;
import com.BookStore.exception.OrderException;
import com.BookStore.repository.BookRepository;
import com.BookStore.repository.OrderRepository;
import com.BookStore.repository.UserRespository;
import com.BookStore.util.OrderTokenUtil;
import com.BookStore.util.SendEmailService;

@Service
public class OrderService implements IOrderService {

	@Autowired
	OrderRepository repository;
	
	@Autowired
	OrderTokenUtil tokenUtil;
	
	@Autowired
	SendEmailService emailService;
	
	@Autowired
	UserModel umodel;
	
	@Autowired
	UserRespository userRepo;
	
	@Autowired
	BookRepository bookRepo;
	
	@Autowired
	ModelMapper modelMapper;

	// Insert the order
	@Override
	public OrderModel InsertOrder(OrderModelDTO model) {

		OrderModel order = new OrderModel(model);
		repository.save(order);
		
		UserModel userToSendMail =  userRepo.findByUserId(model.getUserId());
		
		emailService.sendEmail(userToSendMail.getEmail(),"Order Conformation Email from BookStore ", "WelCome to BookStore"+" "+"\n Your Order is successfully inserted"+" "+
		"\n Please Check your Order details "+" "+
		"\n Book Id is "+model.getBookId()+" "+"\n Order Quantity is:- "+model.getQuantity()
		+"\n your address is:-"+" "+model.getAddress()+"\nTotal Order Cost is "+model.getTotalPrice()+" "+
		"\n\nThank you order for BookStore "+"\n\nplease Don't reply this email");
		
		return order;
	}

	// Get All Order
	@Override
	public List<OrderModel> allOrders() {
		return repository.findAll();
	}

	// Order Get By Id
	@Override
	public OrderModel orderById(int id) {
		Optional<OrderModel> order = repository.findById(id);
		
		if(order.isPresent()) {
			return order.get();
		}
		else throw new OrderException("Order Not Found By id");
	}

	// Delete Order By Id
	@Override
	public String deleteOrder(int id) {
		
		Optional<OrderModel> order = repository.findById(id);
		if (order.isPresent())
		{
			repository.deleteById(id);
			return "Order Deleted Successfully"+id;
		}
		else throw new OrderException("Order is not found");
	}

	// Order Update By Id
	@Override
	public OrderModel updateOrder(int id, OrderModelDTO model) {
		Optional<OrderModel> order = repository.findById(id);
		
		if (order.isPresent())
		{
			OrderModel order1 = modelMapper.map(order, OrderModel.class);
			order1.setOrderId(id);
			repository.save(order1);
			return order1;
		}
		else throw new OrderException("Order Not Found!");
	}

	// Order Cancel by id
	@Override
	public OrderModel cancleOrder(int id) {
		
		Optional<OrderModel> order = repository.findById(id);
		if(order.isPresent()) {
			Optional<BookModel> book = bookRepo.findById(order.get().getBookId());
//			book.get().setQuantity(0);
			order.get().setCancel(true);
			repository.save(order.get());
			
			emailService.sendEmail(userRepo.findByUserId(id).getEmail(),"Cancel order Conformation", "Your Order in the Bookstore is Cancled");
			return order.get();
		}
		else throw new OrderException("Order Not Found");
	}

}
