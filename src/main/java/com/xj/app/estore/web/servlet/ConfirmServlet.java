package com.xj.app.estore.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xj.app.estore.bean.Customer;
import com.xj.app.estore.bean.Order;
import com.xj.app.estore.bean.Orderline;
import com.xj.app.estore.service.ILineService;
import com.xj.app.estore.service.IOrderService;
import com.xj.app.estore.service.impl.LineServiceImpl;
import com.xj.app.estore.service.impl.OrderServiceImpl;
import com.xj.app.estore.shoppingcart.ShoppingCart;

@WebServlet("/user/orderSave")
public class ConfirmServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private IOrderService orderService = new OrderServiceImpl();
	private ILineService lineService = new LineServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/user/confirmOrder.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		
		ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
		Map<Integer, Orderline> lines = shoppingCart.getLines();
		List<Orderline> orderlines = new ArrayList<Orderline>();
		lines.forEach((k,v)->{
			orderlines.add(v);
		});
		
		Order order = new Order();
		order.setCustomer(customer);
		order.setCost(shoppingCart.getCost());
		order.setOrderdate(new Date());
		order.setOrderlines(orderlines);
		
		try {
			orderService.saveOrder(order);
			orderlines.forEach(e->{
				e.setOrder(order);
				lineService.saveLine(e);
				shoppingCart.delete(e.getBook().getId());
			});
			session.removeAttribute("shoppingCart");
			req.getRequestDispatcher("/index.jsp").forward(req, resp);
		} catch (Exception e1) {
			e1.printStackTrace();
			req.getRequestDispatcher("/user/confirmOrder.jsp").forward(req, resp);
		}
		
	}
}
