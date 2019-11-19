package com.xj.app.estore.service;

import com.xj.app.estore.bean.Customer;

public interface ICustomerService {
	
	void login(Customer customer) throws Exception;
	
	void register(Customer customer) throws Exception;
	
	Customer findCustomerByName(String name);
	
	Customer findCustomerById(Integer id);
	
	void update(Customer customer);
	
}
