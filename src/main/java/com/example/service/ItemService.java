package com.example.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Category;
import com.example.entity.Item;
import com.example.mapper.ItemMapper;

@Service
public class ItemService {

	private final ItemMapper itemMapper;
	
	@Autowired
	public ItemService(ItemMapper itemMapper) {
		this.itemMapper = itemMapper;
	}
	// 全件取得
	public List<Item> findAll() {
		return this.itemMapper.findAll();
	}
	
	// 新規登録
	public void insert(String name, Integer price, Integer categoryId) {
		Item item = new Item();
		item.setName(name);
		item.setPrice(price);
		Category category = new Category();
		category.setId(categoryId);
		item.setCategory(category);
		this.itemMapper.insert(item);
	}
	// idで一件取得
	public Item findById(Integer id) {
		return this.itemMapper.findById(id);
	}
//	// 更新
//	public void update(Integer id, String name, Integer price, Integer categoryId) {
//		Item item = new Item();
//		item.setId(id);
//		item.setName(name);
//		item.setPrice(price);
//		Category category = new Category();
//		category.setId(categoryId);
//		item.setCategory(category);
//		this.itemMapper.update(item);
//	}
	
//	// 削除
//	public void deleteById(Integer id) {
//		this.itemMapper.deleteById(id);
//	}
//	
//	// 論理削除
//	public List<Item> findByDeletedAtIsNull() {
//		return itemMapper.findByDeletedAtIsNull();
//	}
//更新
	public Item update(Integer id, String name, Integer price, Integer categoryId, LocalDateTime deletedAt) {
		Item item = new Item();
		item.setId(id);
		item.setName(name);
		item.setPrice(price);
		Category category = new Category();
		category.setId(categoryId);
		item.setCategory(category);
		return this.itemMapper.update(item);
	}
	
//削除 ↓コピー↑
	public Item deleteById(Integer id) {
//		this.itemMapper.deleteById(id);
		Item item = this.findById(id);
		item.setDeletedAt(LocalDateTime.now());
		return itemMapper.update(item);
//		this.update(id, item.getName(), item.getPrice(), item.getCategoryId());
//		return this.itemMapper.deleteById(item);
	}
	
	// DeletedAtがNULLのカラムを取得→表示
	public List<Item> findByDeletedAtIsNull() {
		return itemMapper.findByDeletedAtIsNull();
	}
	
}
