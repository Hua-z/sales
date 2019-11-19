package com.xj.app.estore.mapper;

import java.util.List;

import com.xj.app.estore.bean.Customer;

public interface CustomerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Customer record);

    Customer selectByPrimaryKey(Integer id);

    Customer selectByName(String name);

    List<Customer> selectAll();

    int updateByPrimaryKey(Customer record);
}