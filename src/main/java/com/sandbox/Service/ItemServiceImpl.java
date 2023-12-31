package com.sandbox.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;


import com.sandbox.DTO.ItemDto;
import com.sandbox.Repo.ItemRepo;
import com.sandbox.entity.Item;
import com.sandbox.utils.ItemTypeConverter;

@Service
public class ItemServiceImpl implements ItemService{
	@Autowired
	private ItemRepo itemRepo;
	
	@Autowired
	private ItemTypeConverter itemTypeConverter;


	@Override
	public List<ItemDto> getItemByName(String name) {
		List<Item> resItem = itemRepo.findByName(name);
		List<ItemDto> resItemDtos = resItem.stream().map(itemTypeConverter::dtoGenerator).toList();
		return resItemDtos;
	}

	@Override
	public ItemDto addNewItem(ItemDto itemDto) {
		Item newItem = itemTypeConverter.daoGenerator(itemDto);
		Item savedItem = itemRepo.save(newItem);
		return itemTypeConverter.dtoGenerator(savedItem);
	}

	@Override
	public List<ItemDto> findAllItems() {
		List<Item> items = itemRepo.findAll();
		List<ItemDto> itemDtos = items.stream().map(itemTypeConverter::dtoGenerator).toList();
		return itemDtos;
	}
	
	

}
