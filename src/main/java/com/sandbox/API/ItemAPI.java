package com.sandbox.API;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sandbox.DTO.ItemDto;
import com.sandbox.Service.CustomItemService;
import com.sandbox.Service.ItemService;
import com.sandbox.entity.Item;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/items")
public class ItemAPI {
	@Autowired
	private	MongoTemplate mongoTemplate;
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private CustomItemService customItemService;
	
	@GetMapping
	public ResponseEntity<List<ItemDto>> getAllItems(){
		return new ResponseEntity<>(itemService.findAllItems(),HttpStatus.OK);
		
	}
	
	@GetMapping("/{name}")
	public ResponseEntity<List<ItemDto>> getItemByName(@PathVariable(name = "name") String name) {
		List<ItemDto> item =  itemService.getItemByName(name);
		return new ResponseEntity<>(item,HttpStatus.OK);
	}
	
	@PostMapping("/crud")
	public ResponseEntity<ItemDto> addNewItemUsingCRUD(@RequestBody ItemDto itemDto){
		return new ResponseEntity<>(itemService.addNewItem(itemDto),HttpStatus.CREATED);
	}
	
	@PostMapping
	public ResponseEntity<ItemDto> addNewItemUsingMongoTemplate(@RequestBody ItemDto itemDto){
		return new ResponseEntity<>(customItemService.addNewItem(itemDto),HttpStatus.OK);
	}
	
	@PutMapping("/{name}")
	public ResponseEntity<ItemDto> updateItemByName(@PathVariable(name = "name") String name,@RequestBody ItemDto itemDto){
		return new ResponseEntity<>(customItemService.updateItemByName(name,itemDto),HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/{name}")
	public ResponseEntity<String> deleteItemByName(@PathVariable(name = "name") String name){
		return new ResponseEntity<>(customItemService.deleteItemByName(name),HttpStatus.OK);
	}
}
