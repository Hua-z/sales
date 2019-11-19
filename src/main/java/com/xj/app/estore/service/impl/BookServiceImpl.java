package com.xj.app.estore.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.xj.app.estore.bean.Book;
import com.xj.app.estore.mapper.BookMapper;
import com.xj.app.estore.service.IBookService;
import com.xj.app.estore.util.MyBatisSqlSessionFactory;

public class BookServiceImpl implements IBookService{
	private SqlSession session;
	private BookMapper mapper;

	public BookServiceImpl() {
		session = MyBatisSqlSessionFactory.openSession();
		mapper = session.getMapper(BookMapper.class);
	}
	
	public List<Book> findAll() throws Exception {
		List<Book> list = mapper.selectAll();
		if(list==null) {
			throw new Exception("本店仓库的书已售尽！");
		}
		return list;
	}
	
	public Book findBookById(int id) throws Exception {
		Book book = mapper.selectByPrimaryKey(id);
		if(book==null) {
			throw new Exception("编号为"+id+"的书不存在");
		}
		return book;
	}

}
