package com.ashish.springdemo.dao;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ashish.springdemo.entity.Customer;
@Repository
public class CustomerDAOImpl implements CustomerDao {
	
	
	//need to inject the session factory 
	@Autowired
	private SessionFactory sessionfactory;

	@Override

	public List<Customer> getCustomers() {
		// get the current hibernate session
		Session currentsession=sessionfactory.getCurrentSession();
		
		//create query
		Query<Customer> theQuery=currentsession.createQuery("from Customer order by lastName"
				                                         ,Customer.class);
		//fetch the customers list 
		List<Customer> customers=theQuery.getResultList();
		//return the list 
		
		
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		// TODO Auto-generated method stub
		// get current hibernate session
		Session currentsession = sessionfactory.getCurrentSession();
				
				// save/upate the customer ... finally LOL
		currentsession.saveOrUpdate(theCustomer);	
		
	}

	@Override
	public Customer getCustomer(int id) {
		// get the current hibernate session
				Session currentsession = sessionfactory.getCurrentSession();
				
				// now retrieve/read from database using the primary key
				Customer theCustomer = currentsession.get(Customer.class, id);
				
				return theCustomer;
	}

	@Override
	public void deleteCustomer(int id) {
		// TODO Auto-generated method stub
		// get the current hibernate session
		Session currentsession = sessionfactory.getCurrentSession();
		
		//create query
		Query theQuery = 
				currentsession.createQuery("delete from Customer where id=:customerId");
		//set parameter in the query
		theQuery.setParameter("customerId",id);
		theQuery.executeUpdate();
	}

	@Override
	public List<Customer> searchCustomers(String theSearchName) {

		// get the current hibernate session
		Session currentSession = sessionfactory.getCurrentSession();
		
		Query theQuery = null;
		
		//
		// only search by name if theSearchName is not empty
		//
		if (theSearchName != null && theSearchName.trim().length() > 0) {

			// search for firstName or lastName ... case insensitive
			theQuery =currentSession.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like :theName", Customer.class);
			theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");

		}
		else {
			// theSearchName is empty ... so just get all customers
			theQuery =currentSession.createQuery("from Customer", Customer.class);			
		}
		
		// execute query and get result list
		List<Customer> customers = theQuery.getResultList();
				
		// return the results		
		return customers;	}

}
