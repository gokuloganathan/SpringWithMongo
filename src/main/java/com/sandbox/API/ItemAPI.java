package com.sandbox.API;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sandbox.DTO.ItemDto;
import com.sandbox.Service.ItemService;
import com.sandbox.entity.Item;

@RestController
@RequestMapping("/api/items")
public class ItemAPI {
	@Autowired
	private	MongoTemplate mongoTemplate;
	
	@Autowired
	private ItemService itemService;
	
	@GetMapping("/{name}")
	public ResponseEntity<List<ItemDto>> getItem(@PathVariable(name = "name") String name) {
		List<ItemDto> item =  itemService.getItemByName(name);
		return new ResponseEntity<>(item,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<ItemDto> addNewItem(@RequestBody ItemDto itemDto){
		return new ResponseEntity<>(itemService.addNewItem(itemDto),HttpStatus.CREATED);
	}
}
