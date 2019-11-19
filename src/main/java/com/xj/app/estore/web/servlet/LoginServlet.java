package com.xj.app.estore.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xj.app.estore.bean.Book;
import com.xj.app.estore.bean.Customer;
import com.xj.app.estore.bean.Order;
import com.xj.app.estore.service.IBookService;
import com.xj.app.estore.service.ICustomerService;
import com.xj.app.estore.service.IOrderService;
import com.xj.app.estore.service.impl.BookServiceImpl;
import com.xj.app.estore.service.impl.CustomerServiceImpl;
import com.xj.app.estore.service.impl.OrderServiceImpl;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	private ICustomerService customerService = new CustomerServiceImpl();
	private IBookService bookService = new BookServiceImpl();
	private IOrderService orderService = new OrderServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String password = req.getParameter("password");
		
		HttpSession session = req.getSession();
		
		Customer customer = new Customer();
		customer.setName(name);
		customer.setPassword(password);
		
		
		ServletContext context = req.getServletContext();
		
		String page = "/index.jsp";
		String msg = "";
		try {
			customerService.login(customer);
			Customer customer2 = customerService.findCustomerByName(name);
			session.setAttribute("customer", customer2);
			
			List<Book> list = bookService.findAll();
			context.setAttribute("books", list);
			
			List<Order> list2 = orderService.findOrderByCustomerId(customer2);
			session.setAttribute("orders", list2);
			
		} catch (Exception e) {
			e.printStackTrace();
			page = "/login.jsp";
			msg = e.getMessage();
		}
		
		req.setAttribute("msg", msg);
		req.getRequestDispatcher(page).forward(req, resp);
	}
}
