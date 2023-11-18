package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.entity.Item;

@Mapper
public interface ItemMapper {
	public List<Item> findAll();
	
	public void insert(Item item);
	
	public Item findById(Integer id);
	
//	public void update(Item item); 論理削除変更前
	public Item update(Item item);
	
//	public void deleteById(Integer id); 論理削除変更前
	public Item deleteById(Item item);
	
	public List<Item> findByDeletedAtIsNull(); // DELETED_ATがnullのレコードを取得
	//SELECT * FROM ITEM WHERE DELETED_AT IS NULL
}