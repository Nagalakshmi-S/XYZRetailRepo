package com.xyzretail.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.xyzretail.bean.ItemDetails;
import com.xyzretail.persistence.helper.BasketDaoHelper;

@Repository("persistenceDao")
public class PersistenceDaoImpl implements PersistenceDao {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	@Override
	public ItemDetails searchItemsById(String id) {
		String sql="SELECT * FROM Item_Details where Item_Id= ?";
		ItemDetails item=(ItemDetails) jdbcTemplate.query(sql, new BasketDaoHelper());

		return item;
	}


	@Override
	public boolean searchItemsById(String id, int reqQuantity) {
		ItemDetails item=searchItemsById(id);
		if(item.getAvailableQuantity()>reqQuantity) {
			return true;
		}
		System.out.println("We don't have that much quantity in our store :(");

		return false;
	}

}
