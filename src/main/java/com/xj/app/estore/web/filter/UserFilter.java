package com.xj.app.estore.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.xj.app.estore.bean.Customer;

@WebFilter("/user/*")
public class UserFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		if(customer==null) {
			req.getRequestDispatcher("/login.jsp").forward(request,response);
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		
	}

}
