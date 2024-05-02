package com.ecommerce.service;

import java.util.List;

import com.ecommerce.entity.Category;
import com.ecommerce.responseDTO.CategoryResponse;

public interface CategoryService {

	
	public Category save(Category category);
	
	public List<CategoryResponse> getAllCategories(Integer pageSize);
	
	public Category getCategoryById(Long categoryId);
	
	String deleteCategory(Long categoryId);
	
	public Category updateCategory(Long categoryId, Category updateCategory);
}
