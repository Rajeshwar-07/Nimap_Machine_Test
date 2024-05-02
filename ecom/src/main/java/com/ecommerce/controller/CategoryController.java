package com.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.entity.Category;
import com.ecommerce.responseDTO.CategoryResponse;
import com.ecommerce.service.implementation.CategoriesServiceImplementation;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

	@Autowired
	private CategoriesServiceImplementation categoriesServiceImplementation;

	@PostMapping
	public ResponseEntity<Category> savedCategory(@RequestBody Category category) {
		Category savedCategories = categoriesServiceImplementation.save(category);
		return new ResponseEntity<>(savedCategories, HttpStatus.CREATED);
	}

	@GetMapping("/{CategoryId}")
	public ResponseEntity<Category> geyCategoryById(@PathVariable Long CategoryId) {
		Category category = categoriesServiceImplementation.getCategoryById(CategoryId);
		return ResponseEntity.ok(category);
	}

	@GetMapping
	public ResponseEntity<List<CategoryResponse>> getAllCategories(
			@RequestParam(value = "pageSize", defaultValue = "2", required = false) Integer pageSize) {
		List<CategoryResponse> categoriesList = categoriesServiceImplementation.getAllCategories(pageSize);
		return ResponseEntity.ok(categoriesList);
	}

	@DeleteMapping("/{categoryId}")
	public ResponseEntity<String> deleteCategoryById(@PathVariable Long categoryId) {
		String result = categoriesServiceImplementation.deleteCategory(categoryId);
		return ResponseEntity.ok(result);
	}

	@PutMapping("/{categoryId}")
	public ResponseEntity<Category> updateCategory(@PathVariable Long categoryId, @RequestBody Category category) {
		Category updateCategory = categoriesServiceImplementation.updateCategory(categoryId, category);
		return new ResponseEntity<>(updateCategory, HttpStatus.CREATED);
	}

}
