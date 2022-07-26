	package com.xyzretail.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.xyzretail.bean.Customer;
import com.xyzretail.bean.Transaction;

@Repository("transactionDao")
public class TransactionDaoImpl implements TransactionDao{
	

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Transaction getTransactionDetails(Customer customerName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean performTransaction(String customer) {

		int rows = 0;
		try {
			
		String query ="INSERT INTO transactionTable (User_Name, transaction_date, transaction_time) values(?,current_date(), current_Time())";
			
			
			rows =jdbcTemplate.update(query,customer);
	

		} catch (Exception e) {
			System.out.println("Transaction couldn't complete");
			System.out.println("exception occured \n"+ e);
		}

		if(rows!=0)
			return true;
		

		return false;
	}

	@Override
	public boolean updateTransaction(Customer customerName, String itemId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void insertIntoOrderTable(String customer) {

		int rows = 0;
		String query1 ="select max(transactionId ) as maxid from transactionTable;";
		String query2="insert ignore into orders select t.transactionId , i.itemId, requiredQuantity from itemsCart i, transactionTable t where (transactionId=? )and t.User_Name=i.User_Name;";
			
				
		Integer transacId =jdbcTemplate.queryForObject(query1,Integer.class);
		rows = jdbcTemplate.update(query2,transacId);

		if(rows!=0)
			System.out.println();
		else 
			System.out.println("couldn't update your Order table  !!");
		
		
		
	}
	

	@Override
	public int monthCount(String customer) {
	
		String query1= "select count(*) as monthCount from transactionTable where user_Name=? and month(now())=month(transaction_Date) and year(now())=year(transaction_Date);" ;

			jdbcTemplate.update(query1,customer);
	        return jdbcTemplate.queryForObject(query1, Integer.class);

	}
}

