package com.xj.app.estore.mapper;

import java.util.List;

import com.xj.app.estore.bean.Orderline;

public interface OrderlineMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Orderline record);

    Orderline selectByPrimaryKey(Integer id);

    List<Orderline> selectAll();

    int updateByPrimaryKey(Orderline record);
    
    List<Orderline> selectByOrderId(Integer orderId);
    
    void deleteByOrderId(Integer orderId);
}