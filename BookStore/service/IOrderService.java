package com.BookStore.service;

import java.util.List;

import com.BookStore.dto.OrderModelDTO;
import com.BookStore.entity.OrderModel;

public interface IOrderService {

//	OrderModel InsertOrder(OrderModel model);

	List<OrderModel> allOrders();

	OrderModel orderById(int id);

	String deleteOrder(int id);
	
	OrderModel updateOrder(int id, OrderModelDTO model);

	OrderModel cancleOrder(int id);

	OrderModel InsertOrder(OrderModelDTO model);





	

}
