package com.xj.app.estore.service;

import java.util.List;

import com.xj.app.estore.bean.Customer;
import com.xj.app.estore.bean.Order;

public interface IOrderService {
	void saveOrder(Order order) throws Exception;
	List<Order> findAll() throws Exception;
	List<Order> findOrderByCustomerId(Customer customer) throws Exception;
	Order findByOrderId(Integer id) throws Exception;
	void deleteOrder(Integer id) throws Exception;
}
