package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.entity.Category;
import com.example.entity.Item;
import com.example.form.ItemForm;
import com.example.service.CategoryService;
import com.example.service.ItemService;

@Controller
public class ItemController {
	
	private final ItemService itemService;
	private final CategoryService categoryService;
	
	@Autowired
	public ItemController(ItemService itemService, CategoryService categoryService) {
		this.itemService = itemService;
		this.categoryService = categoryService;
	}
	
	@GetMapping("/index")
	public String index(Model model) {
//		List<Item> items = itemService.findAll();
		List<Item> items = this.itemService.findByDeletedAtIsNull(); // 論理削除
		model.addAttribute("items", items);
		return "index";
	}
	
	// 新規作成画面
	@GetMapping("/create")
	public String createPage(@ModelAttribute ItemForm itemForm, Model model) {
		List<Category> categories = this.categoryService.findAll();
		model.addAttribute("categories", categories);
		return "create";
	}
	
	// 新規作成
	@PostMapping("/create")
	public String create(@ModelAttribute ItemForm itemForm) {
		this.itemService.insert(itemForm.getName(),
														itemForm.getPrice(),
														itemForm.getCategoryId());
//		this.categoryService.save(itemForm);
		return "redirect:/index";
	}
	
	// 更新画面
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Integer id, Model model, @ModelAttribute ItemForm itemForm) {
		Item item = this.itemService.findById(id);
		itemForm.setName(item.getName());
		itemForm.setPrice(item.getPrice());
		itemForm.setCategoryId(item.getCategory().getId());
		List<Category> categories = this.categoryService.findAll(); // カテゴリーの情報をプルダウンで使用するため
		model.addAttribute("categories", categories);
		
		model.addAttribute("id", id); // ←忘れないように
		return "edit";
	}
	// ↓論理削除変更前
//	// 更新
//	@PostMapping("/edit/{id}")
//	public String update(@PathVariable Integer id, ItemForm itemForm) {
//		this.itemService.update(id, itemForm.getName(), itemForm.getPrice(), itemForm.getCategoryId());
//	return "redirect:/index";
//	}
	
// 更新
@PostMapping("/edit/{id}")
public String update(@PathVariable Integer id, ItemForm itemForm) {
	this.itemService.update(id, itemForm.getName(), itemForm.getPrice(), itemForm.getCategoryId(), itemForm.getDeletedAt());
return "redirect:/index";
}
	
  // 論理削除時 DeletedAt=NULLかどうか
	@PostMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) {
		this.itemService.deleteById(id);
		return "redirect:/index";
	}

}
