package com.BookStore.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BookStore.dto.CartModelDTO;
import com.BookStore.entity.BookModel;
import com.BookStore.entity.CartModel;
import com.BookStore.exception.BookException;
import com.BookStore.exception.CartException;
import com.BookStore.repository.CartRepository;
import com.BookStore.util.CartTokenUtil;

@Service
public class CartService implements ICartService {

	@Autowired
	CartRepository repository;
	
	@Autowired
	CartTokenUtil tokenUtil;
	
	@Autowired
	ModelMapper modelMapper;
	
	// Insert the Cart
	@Override
	public CartModel insertCart(CartModelDTO model) {
		CartModel user = new CartModel(model);
		repository.save(user);
		
		String token = tokenUtil.createToken(user.getCartId());
		System.out.println(token);
		return user;
	}

	// Get All Carts
	@Override
	public List<CartModel> getAllCart() {
		return repository.findAll();
	}

	// Get Cart By Id
	@Override
	public CartModel CartById(int id) {
		Optional<CartModel> user = repository.findById(id);
		
		if(user.isPresent()) {
			return user.get();
		}
		else throw new CartException("Cart Not Found") ;
	}

	// Delete Cart by id
	@Override
	public String deleteCart(int id) {
	
		Optional<CartModel> user = repository.findById(id);
		if (user.isPresent()) {
			repository.deleteById(id);
			return "Delete By id Successfully"+id;
		}
		else throw new CartException("Cart delete Successfully !");
	}

	// Update Carts by id
	@Override
	public CartModel CartUpdate(int id, CartModelDTO model) {
	
		Optional<CartModel> cart = repository.findById(id);
		if(cart.isPresent())
		{
			
			CartModel cart1 = modelMapper.map(cart, CartModel.class);
			cart1.setCartId(id);
			repository.save(cart1);
			return cart1;
		}
		else throw new CartException("Cart Not Found");
	}
	

	// Update By Quantity
	@Override
	public CartModel UpdateQuantity(int id, CartModelDTO model) {
	
		Optional<CartModel> user = repository.findById(id);
		if(user.isPresent())
		{
			user.get().setQuantity(model.getQuantity());
			repository.save(user.get());
			return user.get();
		}
		else throw new CartException("Cart not found");
		
	}

	
}
