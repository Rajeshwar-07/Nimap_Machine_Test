package com.ecommerce.service.implementation;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ecommerce.entity.Category;
import com.ecommerce.repository.CategoryRepo;
import com.ecommerce.responseDTO.CategoryResponse;
import com.ecommerce.service.CategoryService;

@Service
public class CategoriesServiceImplementation implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;

	// Save category
	public Category save(Category category) {
		Category saveCategory = categoryRepo.save(category);
		return saveCategory;
	}

	// Get all category records
	public List<CategoryResponse> getAllCategories(Integer pageSize) {
		Pageable pageable = PageRequest.ofSize(pageSize);
		Page<Category> pageCategory = categoryRepo.findAll(pageable);
		List<CategoryResponse> categoryResponse = pageCategory.getContent().stream()
				.map(category -> mapToCategoryResponse(category)).collect(Collectors.toList());
		return categoryResponse;
	}

	
	// Get category records by id
	public Category getCategoryById(Long categoryId) {
		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new NoSuchElementException("Resource not present"));
		return category;
	}

	// Delete category records by id
	public String deleteCategory(Long categoryId) {
		if (!categoryRepo.existsById(categoryId)) {
			throw new NoSuchElementException("Resource not present");
		}
			categoryRepo.deleteById(categoryId);
			return "Category deleted success";			
	}

	// Update category records
	public Category updateCategory(Long categoryId, Category updateCategory) {
		Category existingCategory = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new NoSuchElementException("Resource not present"));
		existingCategory.setCategoryName(updateCategory.getCategoryName());
		return categoryRepo.save(existingCategory);
	}

	private CategoryResponse mapToCategoryResponse(Category category) {
		CategoryResponse categoryResponse = new CategoryResponse();
		categoryResponse.setCategoryId(category.getCategoryId());
		categoryResponse.setCategoryName(category.getCategoryName());
		return categoryResponse;
	}
}
