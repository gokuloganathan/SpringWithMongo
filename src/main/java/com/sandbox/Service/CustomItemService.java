package com.sandbox.Service;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.mongodb.client.result.DeleteResult;
import com.sandbox.DTO.ItemDto;
import com.sandbox.entity.Item;
import com.sandbox.utils.ItemTypeConverter;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomItemService {
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private ItemTypeConverter itemTypeConverter;
	
	public ItemDto addNewItem(ItemDto itemDto) {
		Item newItem = itemTypeConverter.daoGenerator(itemDto);
		Item item = mongoTemplate.insert(newItem);
		log.info("added new item of id : "+item.getId());
		return itemTypeConverter.dtoGenerator(item);
	}

	public String deleteItemByName(String name) {
		Query query = new Query();
		query.addCriteria(Criteria.where("name").is(name));
		
		DeleteResult deletedResult = mongoTemplate.remove(query, Item.class);
		return deletedResult.toString();
	}

	public ItemDto updateItemByName(String name,ItemDto itemDto) {
		Query query = new Query();
		query.addCriteria(Criteria.where("name").is(name));
		
		
		Update update = new Update();
		if(itemDto.getPrice() == 0)
		update.set("price", new Random().nextInt(99));
		else
		if(itemDto.getQty() == 0)
		update.set("qty",new Random().nextInt(9));
		update.set("category", itemDto.getCategory());
		
	   mongoTemplate.updateFirst(query, update, Item.class);
	   
	   Item savedItem = mongoTemplate.findOne(query, Item.class);		
		return itemTypeConverter.dtoGenerator(savedItem);
	}
		
}
