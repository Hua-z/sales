package com.xj.app.estore.service.impl;

import org.apache.ibatis.session.SqlSession;

import com.xj.app.estore.bean.Customer;
import com.xj.app.estore.mapper.CustomerMapper;
import com.xj.app.estore.service.ICustomerService;
import com.xj.app.estore.util.MyBatisSqlSessionFactory;

public class CustomerServiceImpl implements ICustomerService {
	
	private SqlSession session;
	private CustomerMapper mapper;
	
	public CustomerServiceImpl() {
		session = MyBatisSqlSessionFactory.openSession();
		mapper = session.getMapper(CustomerMapper.class);
	}

	@Override
	public void register(Customer customer) throws Exception {
		Customer customerFromDB = mapper.selectByName(customer.getName());
		if(customerFromDB!=null) {
			throw new Exception("用户名"+customer.getName()+"已经存在！");
		}
		mapper.insert(customer);
		session.commit();
	}

	@Override
	public void login(Customer customer) throws Exception {
		Customer c = mapper.selectByName(customer.getName());
		if(c!=null) {
			if(!c.getPassword().equals(customer.getPassword())) {
				throw new Exception("密码错误!请重新进行输入！");
			}
		}else{
			throw new Exception("用户名"+customer.getName()+"不存在，请先注册！");
		}
	}

	@Override
	public Customer findCustomerByName(String name) {
		Customer customer = mapper.selectByName(name);
		return customer;
	}

	@Override
	public void update(Customer customer) {
		mapper.updateByPrimaryKey(customer);
		session.commit();
	}

	@Override
	public Customer findCustomerById(Integer id) {
		Customer customer = mapper.selectByPrimaryKey(id);
		return customer;
	}

}
