package com.sandbox.Repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.sandbox.entity.Item;

public interface ItemRepo extends MongoRepository<Item, String>{
	@Query(value = "${name:'?0'}")
	public List<Item> findByName(String name);
}
