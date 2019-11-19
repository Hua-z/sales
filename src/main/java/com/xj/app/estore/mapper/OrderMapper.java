package com.xj.app.estore.mapper;

import java.util.List;

import com.xj.app.estore.bean.Order;

public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    Order selectByPrimaryKey(Integer id);

    List<Order> selectAll();

    int updateByPrimaryKey(Order record);
    
    List<Order> selectByCustomerId(Integer customerId);
}