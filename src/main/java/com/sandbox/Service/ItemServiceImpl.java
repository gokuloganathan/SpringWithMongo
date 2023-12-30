package com.sandbox.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;


import com.sandbox.DTO.ItemDto;
import com.sandbox.Repo.ItemRepo;
import com.sandbox.entity.Item;

@Service
public class ItemServiceImpl implements ItemService{
	@Autowired
	private ItemRepo itemRepo;
	

	@Override
	public List<ItemDto> getItemByName(String name) {
		List<Item> resItem = itemRepo.findByName(name);
		List<ItemDto> resItemDtos = resItem.stream().map(item -> ItemDto.builder().id(item.getId()).name(item.getName()).price(item.getPrice()).build()).toList();
		return resItemDtos;
	}

	@Override
	public ItemDto addNewItem(ItemDto itemDto) {
		Item newItem = Item.builder().name(itemDto.getName()).price(itemDto.getPrice()).build();
		Item savedItem = itemRepo.save(newItem);
		return ItemDto.builder().id(savedItem.getId()).name(savedItem.getName()).price(savedItem.getPrice()).build();
	}

}
