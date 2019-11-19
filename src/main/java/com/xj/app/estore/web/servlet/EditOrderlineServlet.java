package com.xj.app.estore.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xj.app.estore.shoppingcart.ShoppingCart;

@WebServlet("/user/updateProduct")
public class EditOrderlineServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/user/shoppingCart.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String bookid = req.getParameter("bookid");
		String num = req.getParameter("num");
		
		HttpSession session = req.getSession();
		ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
		
		shoppingCart.update(Integer.parseInt(bookid), Integer.parseInt(num));
		
		req.getRequestDispatcher("/user/shoppingCart.jsp").forward(req, resp);
	}
}
