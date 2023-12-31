package com.sandbox.utils;

import org.springframework.stereotype.Component;

import com.sandbox.DTO.ItemDto;
import com.sandbox.entity.Item;

@Component
public class ItemTypeConverter {
	
	public ItemDto dtoGenerator(Item item){
		return ItemDto.builder()
				.id(item.getId())
				.name(item.getName())
				.price(item.getPrice())
				.qty(item.getQty())
				.category(item.getCategory())
				.build();
	}
	
	public Item daoGenerator(ItemDto itemdto){
		return Item.builder()
				.id(itemdto.getId())
				.name(itemdto.getName())
				.price(itemdto.getPrice())
				.qty(itemdto.getQty())
				.category(itemdto.getCategory())
				.build();
	}

}
