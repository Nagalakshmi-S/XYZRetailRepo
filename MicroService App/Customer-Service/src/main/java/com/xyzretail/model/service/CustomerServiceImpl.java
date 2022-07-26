package com.xyzretail.model.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyzretail.bean.Customer;
import com.xyzretail.model.persistence.CustomerDao;
@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerDao customerDao;

	@Override
	public Customer customerByName(String user_Name) {
		return customerDao.findById(user_Name).orElse(null);
	}

	@Override
	public List<Customer> getAllCustomer() {
		
		return customerDao.findAll();
	}

//	@Override
//	public boolean registerCustomer(Customer customer) {			
//		Optional<Customer> cus = customerDao.findById(customer.getUserName());		// check if entered username is present or not in table
//		if (cus.isPresent()) {
//			return false ;		
//		}
//		customerDao.save(customer);
//		return true;
	public boolean registerCustomer(Customer customer) {
		Customer cus= customerDao.findById(customer.getUserName()).orElse(null);		// check if username is already present in the table or not
		if (cus== null) {			// if username is not present i.e.  null
			customerDao.save(customer);
			return true;
		}
		return false;		// return the customer (bcs it is a new customer )
		
	}

	
	@Override
	public boolean registerCustomerByParam(String userName ,String password) {
		Customer cus= customerDao.findById(userName).get();		// check if username is already present in the table or not
		if (cus!=null) {			// if username is not present i.e.  null
			int rows=customerDao.registerCustomer(userName, password);		// return the customer (bcs it is a new customer )
			if(rows>0) {
				return true;
			}
		}	
		return false;
	}
	@Override
	public Customer loginCustomer(String user_Name, String user_Password) {
		Customer cus = customerDao.findById(user_Name).orElse(null);		// check if entered username is present or not in table
		if(cus!= null)					// if username is present 
			if(user_Password.equals(cus.getUserPassword()))    		// check the password enetred is matching or not with table
				return customerDao.findById(user_Name).get();						// if username and password matches return the customer  // (or we could have returned true :(   )
		return null;									// if password or username doesnt match return null
	}

}
