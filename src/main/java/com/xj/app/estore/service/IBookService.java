package com.xj.app.estore.service;

import java.util.List;

import com.xj.app.estore.bean.Book;

public interface IBookService {
	List<Book> findAll() throws Exception;
	Book findBookById(int id) throws Exception;
}
