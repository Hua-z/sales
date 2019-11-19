package com.xj.app.estore.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xj.app.estore.bean.Book;
import com.xj.app.estore.bean.Orderline;
import com.xj.app.estore.service.IBookService;
import com.xj.app.estore.service.impl.BookServiceImpl;
import com.xj.app.estore.shoppingcart.ShoppingCart;

@WebServlet("/addOrderline")
public class AddOrderlineServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	IBookService bookService = new BookServiceImpl();
	static ShoppingCart shoppingCart = new ShoppingCart();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String bookid = req.getParameter("bookid");
//		HttpSession session = req.getSession();
//		Customer customer = (Customer) session.getAttribute("customer");
		
		Book book = new Book();
		try {
			book = bookService.findBookById(Integer.parseInt(bookid));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Orderline orderline = new Orderline();
		orderline.setBook(book);
		
		shoppingCart.add(orderline);
		
		HttpSession session = req.getSession();
		session.removeAttribute("shoppingCart");
		session.setAttribute("shoppingCart", shoppingCart);
		
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}

}
