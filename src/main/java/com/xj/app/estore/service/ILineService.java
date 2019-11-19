package com.xj.app.estore.service;

import java.util.List;

import com.xj.app.estore.bean.Orderline;

public interface ILineService {
	void saveLine(Orderline line);
	List<Orderline> findLineByOrderId(Integer orderId);
	void deleteLineByOrderId(Integer orderId);
}
