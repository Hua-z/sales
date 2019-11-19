package com.xj.app.estore.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.xj.app.estore.bean.Book;
import com.xj.app.estore.mapper.BookMapper;
import com.xj.app.estore.util.MyBatisSqlSessionFactory;

@WebServlet("/test")
public class TestServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SqlSession session = MyBatisSqlSessionFactory.openSession();
		BookMapper mapper = session.getMapper(BookMapper.class);
		
		Book book = new Book();
		book.setName("test");
		book.setPrice(100D);
		System.out.println("brfore:"+book.getId());
		mapper.insert(book);
		System.out.println("after:"+book.getId());
		
		session.commit();
	}
}
