package com.roommanagement.demo.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.roommanagement.demo.model.Item;
import com.roommanagement.demo.repository.ItemRepository;
import com.roommanagement.demo.repository.model.ItemDetails;

@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;

	public void addItem(Item item) {
		ItemDetails itemDetails = new ItemDetails();
		System.out.println("printing item :" + item.getDateOfPurchase());

		java.util.Date d = null;
		try {
			d = new SimpleDateFormat("dd/MM/yyyy").parse("10/05/2020");

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // This throws a ParseException

		// Rest everything stays pretty much the same
		java.sql.Date d1 = new java.sql.Date(d.getTime());


		itemDetails.setDateOfPurchase(d1);
		itemDetails.setListOfItems(item.getListOfItems());
		itemDetails.setPrice(item.getPrice());
		itemDetails.setUserEmail(item.getUserEmail());
		itemRepository.save(itemDetails);
	}

	public List<ItemDetails> getAllItems() {
		List<ItemDetails> listOfItems=itemRepository.findAll();
		return listOfItems;
		
	}

}
