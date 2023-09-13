package com.BookStore.service;

import java.util.List;

import com.BookStore.dto.CartModelDTO;
import com.BookStore.entity.CartModel;

public interface ICartService {

	CartModel insertCart(CartModelDTO model);

	List<CartModel> getAllCart();

	CartModel CartById(int id);

	String deleteCart(int id);

	CartModel CartUpdate(int id, CartModelDTO model);

	CartModel UpdateQuantity(int id, CartModelDTO model);

	

}
