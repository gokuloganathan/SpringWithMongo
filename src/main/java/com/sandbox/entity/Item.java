package com.sandbox.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Document(collection = "items")
@NoArgsConstructor
@AllArgsConstructor
public class Item {
	@Id
	private String id;
	
	@Field
	private String name;
	
	@Field
	private int price;
	
	@Field
	private int qty;
	
	@Field
	private String category;
}
