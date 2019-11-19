package com.xj.app.estore.bean;

import java.util.ArrayList;
import java.util.List;

public class Book {
    private Integer id;

    private String name;

    private Double price;
    
    List<Orderline> orderlines;

    public List<Orderline> getOrderlines() {
    	if(orderlines == null) {
    		orderlines = new ArrayList<Orderline>();
    	}
    	return orderlines;
	}

	public void setOrderlines(List<Orderline> orderlines) {
		this.orderlines = orderlines;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}