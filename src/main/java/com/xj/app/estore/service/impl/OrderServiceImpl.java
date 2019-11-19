package com.xj.app.estore.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.xj.app.estore.bean.Customer;
import com.xj.app.estore.bean.Order;
import com.xj.app.estore.bean.Orderline;
import com.xj.app.estore.mapper.OrderMapper;
import com.xj.app.estore.service.ICustomerService;
import com.xj.app.estore.service.ILineService;
import com.xj.app.estore.service.IOrderService;
import com.xj.app.estore.util.MyBatisSqlSessionFactory;

public class OrderServiceImpl implements IOrderService{
	private SqlSession session;
	private OrderMapper mapper;
	private ICustomerService customerService = new CustomerServiceImpl();
	private ILineService lineService = new LineServiceImpl();
	
	public OrderServiceImpl() {
		session = MyBatisSqlSessionFactory.openSession();
		mapper = session.getMapper(OrderMapper.class);
	}

	@Override
	public void saveOrder(Order order) throws Exception{
		mapper.insert(order);
		session.commit();
	}

	@Override
	public List<Order> findAll() throws Exception {
		List<Order> list = mapper.selectAll();
		return list;
	}

	@Override
	public List<Order> findOrderByCustomerId(Customer customer) throws Exception {
		List<Order> list = mapper.selectByCustomerId(customer.getId());
		return list;
	}

	@Override
	public Order findByOrderId(Integer id) throws Exception {
		Order order = mapper.selectByPrimaryKey(id);
		Integer customerId = order.getCustomer().getId();
		
		Customer customer = customerService.findCustomerById(customerId);
		
		order.setCustomer(customer);
		
		List<Orderline> list = lineService.findLineByOrderId(id);
		
		order.setOrderlines(list);
		return order;
	}

	@Override
	public void deleteOrder(Integer id) throws Exception {
		ILineService lineService = new LineServiceImpl();
		lineService.deleteLineByOrderId(id);
		mapper.deleteByPrimaryKey(id);
		session.commit();
	}

}
