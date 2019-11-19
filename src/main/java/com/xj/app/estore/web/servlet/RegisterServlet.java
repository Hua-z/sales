package com.xj.app.estore.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xj.app.estore.bean.Customer;
import com.xj.app.estore.service.ICustomerService;
import com.xj.app.estore.service.impl.CustomerServiceImpl;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	private ICustomerService customerService = new CustomerServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//请求转发到注册页面
		String path = "/register.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1.接收请求中携带参数
		String name = req.getParameter("name");
		String password = req.getParameter("password");
		String address = req.getParameter("address");
		String zip = req.getParameter("zip");
		String telephone = req.getParameter("telephone");
		String email = req.getParameter("email");
		
		//2.把参数封装成对象（可选）
		Customer customer = new Customer();
		customer.setName(name);
		customer.setPassword(password);
		customer.setAddress(address);
		customer.setZip(zip);
		customer.setTelephone(telephone);
		customer.setEmail(email);
		
		//3.调用service层方法进行逻辑处理
		String page = "/login.jsp";
		String msg = "注册成功！请登录";
		try {
			/**如果方法抛出异常 则注册失败*/
			/**如果方法不抛出异常 则注册成功*/
			customerService.register(customer);
		} catch (Exception e) {
			e.printStackTrace();
			page = "/register.jsp";
			msg = "注册失败："+e.getMessage();
		}
		
		//4.根据service层的处理结果 选择合适的页面返回给浏览器
		req.setAttribute("msg", msg);//携带数据到所转向的页面中去
		req.getRequestDispatcher(page).forward(req, resp);
	}
}
