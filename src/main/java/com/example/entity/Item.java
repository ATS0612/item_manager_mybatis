package com.example.entity;

import java.time.LocalDateTime;

//@Entity
//@Table(name = "ITEMS")
public class Item {

//	@Id
//	@SequenceGenerator(name = "ITEM_ID_GENERATOR", sequenceName = "ITEM_ID_SEQ", allocationSize = 1)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ITEM_ID_GENERATOR")
//	@Column(name = "ID")
	private Integer id;

//	@Column(name = "NAME")
	private String name;

//	@Column(name = "PRICE")
	private Integer price;

//	@Column(name = "DELETED_AT")
	private LocalDateTime deletedAt;

//	@Column(name = "CATEGORY_ID")
	private Integer categoryId;

//	@Column(name = "STOCK")
	private Integer stock;
	
	private Category category;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPrice() {
		return this.price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	
	// 論理削除のgetterとsetter
	public LocalDateTime getDeletedAt() {
		return this.deletedAt;
	}

	public void setDeletedAt(LocalDateTime deletedAt) {
		this.deletedAt = deletedAt;
	}

	//    @ManyToOne
	//    @JoinColumn(name = "category_id", insertable = false, updatable = false)
	//    private Category category;
	//
	//    public Category getCategory() {
	//        return this.category;
	//    }

	public Integer getStock() {
		return this.stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}
	
	public Category getCategory() {
		return this.category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}
}
