
package com.xyzretail.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.xyzretail.bean.Customer;
import com.xyzretail.persistence.helper.CustomerDaoHelper;

@Repository("customerDao")
public class CustomerDaoImpl implements CustomerDao {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int addCustomer(Customer customer) {
		
//		String sql="INSERT INTO customer values(?,?)"; 
//		int rows=jdbcTemplate.update(sql, customer.getUserName(),customer.getUserPassword());
//		
//		
//		return rows;
		
		int rows = 0;
		String query ="INSERT INTO customer values(?,?)";
		try {				
			rows = jdbcTemplate.update(query,customer.getUserName(), customer.getUserPassword());

		} catch (Exception e) {
			System.out.println("This UserName is already taken, try for another one :)");
		}	
		return rows;
	}
	

	@Override
	public Customer validateCustomer(Customer customer) {
		String sql = "SELECT * FROM CUSTOMER WHERE USER_NAME = ?";
		Customer cus = jdbcTemplate.queryForObject(sql, new CustomerDaoHelper(),customer.getUserName());
		return cus;
	}
	
}
