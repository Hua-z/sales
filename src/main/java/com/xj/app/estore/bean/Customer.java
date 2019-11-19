package com.xj.app.estore.bean;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private Integer id;

    private String name;

    private String password;

    private String zip;

    private String address;

    private String telephone;

    private String email;
    
    private List<Order> orders;
    
    @Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", password=" + password + ", zip=" + zip + ", address="
				+ address + ", telephone=" + telephone + ", email=" + email + ", orders=" + orders + "]";
	}

	public List<Order> getOrder() {
    	if(orders == null) {
    		orders = new ArrayList<Order>();
    	}
		return orders;
	}

	public void setOrder(List<Order> orders) {
		this.orders = orders;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}