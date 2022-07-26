package com.xyzretail.resource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xyzretail.bean.ItemDetail;
import com.xyzretail.bean.ItemDetailsList;
import com.xyzretail.persistence.ItemDetailDao;
import com.xyzretail.service.ItemDetailService;

@RestController
public class ItemDetailResource {
	@Autowired
	private ItemDetailService itemDetailService;
//	@Autowired
//	private ItemDetailDao itemDetailDao;
	
	@GetMapping(path="/itemDetail", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ItemDetailsList> getAllItemDetailResource() {
		ItemDetailsList itemDetailsList =new ItemDetailsList(itemDetailService.getAllItemDetails());
		return new ResponseEntity<ItemDetailsList> (itemDetailsList,HttpStatus.FOUND );

	}
	
	@GetMapping(path="/itemDetail/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ItemDetail getItemByIdResource(@PathVariable("id")String itemId) {
		ItemDetail item=itemDetailService.findByItemId(itemId);
		System.out.println(itemId);
		if(item!=null)
			return item;
		else
			return new ItemDetail();
	}
	
	@GetMapping(path="/itemDetail/{id}/{quantity}",produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean getItemByIdAndQuantityResource(@PathVariable("id")String itemId,@PathVariable("quantity")int availableQuantity) {
		ItemDetail id =itemDetailService.findByItemId_AndAvailable_Quantity(itemId, availableQuantity);
		if(id!=null)
			return true;
		else 
			return false;
	}
	
	@PutMapping(path="/itemDetail/{id}/{quantity}",produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean updateRecord(@PathVariable("id")String itemId,@PathVariable("quantity") int quantity) {
		ItemDetail item=itemDetailService.updateRecord(itemId,quantity);
		if(item!=null)
			return true;
		return false;
	}
	
}










