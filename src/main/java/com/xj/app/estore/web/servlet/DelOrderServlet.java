package com.xj.app.estore.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xj.app.estore.bean.Order;
import com.xj.app.estore.service.IOrderService;
import com.xj.app.estore.service.impl.OrderServiceImpl;

@WebServlet("/user/orderRemove")
public class DelOrderServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private IOrderService orderService = new OrderServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String orderId = req.getParameter("id");
		HttpSession session = req.getSession();
		session.removeAttribute("orders");
		try {
			orderService.deleteOrder(Integer.parseInt(orderId));
			List<Order> list = orderService.findAll();
			session.setAttribute("orders", list);
			req.getRequestDispatcher("/user/order.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/user/order.jsp").forward(req, resp);
	}
}
