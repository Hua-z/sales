package com.xj.app.estore.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.xj.app.estore.bean.Book;
import com.xj.app.estore.bean.Orderline;
import com.xj.app.estore.mapper.OrderlineMapper;
import com.xj.app.estore.service.IBookService;
import com.xj.app.estore.service.ILineService;
import com.xj.app.estore.util.MyBatisSqlSessionFactory;

public class LineServiceImpl implements ILineService{
	private SqlSession session;
	private OrderlineMapper mapper;
	private IBookService bookService = new BookServiceImpl();
	
	public LineServiceImpl() {
		session = MyBatisSqlSessionFactory.openSession();
		mapper = session.getMapper(OrderlineMapper.class);
	}

	@Override
	public void saveLine(Orderline line) {
		mapper.insert(line);
		session.commit();
	}

	@Override
	public List<Orderline> findLineByOrderId(Integer orderId) {
		List<Orderline> list = mapper.selectByOrderId(orderId);
		list.forEach(e->{
			try {
				Book book = bookService.findBookById(e.getBook().getId());
				e.setBook(book);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		return list;
	}

	@Override
	public void deleteLineByOrderId(Integer orderId) {
		mapper.deleteByOrderId(orderId);
		session.commit();
	}
	
}
