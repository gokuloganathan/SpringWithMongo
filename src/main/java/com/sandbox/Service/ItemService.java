package com.sandbox.Service;

import java.util.List;

import com.sandbox.DTO.ItemDto;


public interface ItemService {
	List<ItemDto> getItemByName(String name);
	
	ItemDto addNewItem(ItemDto itemDto);

	List<ItemDto> findAllItems();

}
