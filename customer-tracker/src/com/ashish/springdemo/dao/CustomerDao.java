package com.ashish.springdemo.dao;

import java.util.List;

import com.ashish.springdemo.entity.Customer;

public interface CustomerDao {

	
	public List<Customer> getCustomers();

	public void saveCustomer(Customer theCustomer);

	public Customer getCustomer(int id);

	public void deleteCustomer(int id);

	public List<Customer> searchCustomers(String theSearchName);
}