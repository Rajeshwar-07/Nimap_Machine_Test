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
import com.ecommerce.entity.Products;
import com.ecommerce.repository.CategoryRepo;
import com.ecommerce.repository.ProductsRepo;
import com.ecommerce.responseDTO.CategoryResponse;
import com.ecommerce.responseDTO.ProductResponse;
import com.ecommerce.service.ProductService;

@Service
public class ProductServiceImplementation implements ProductService {

	@Autowired
	private ProductsRepo productsRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	// Save product record
	public Products saveProduct(Products products, Long categoryId) {
		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new NoSuchElementException("Resource not present"));
		products.setCategory(category);
		return productsRepo.save(products);
	}

	// Fetch all product records
	public List<ProductResponse> getAllProducts(Integer pageSize) {
		Pageable pageable = PageRequest.ofSize(pageSize);
		Page<Products> pagePost = this.productsRepo.findAll(pageable);
		List<Products> allProducts = pagePost.getContent();
		return allProducts.stream().map(this::mapProductEntityToProductResponseDto).collect(Collectors.toList());
	}

	// Fetch single product record along with category name
	public ProductResponse getProductById(Long productId) {
		Products product = productsRepo.findById(productId)
				.orElseThrow(() -> new NoSuchElementException("Resource not present"));
		ProductResponse productResponse = new ProductResponse();
		productResponse.setProductId(product.getProductId());
		productResponse.setProductName(product.getProductName());
		productResponse.setProductPrice(product.getProductPrice());
		productResponse.setProductQuantity(product.getProductQuantity());
		productResponse.setCategoryName(mapCategoryEntityToCategoryDto(product));
		return productResponse;
	}

	// Deleting the product record
	public String deleteProduct(Long productId) {
		if (!productsRepo.existsById(productId)) {
			throw new NoSuchElementException("Resource not present");
		}
		productsRepo.deleteById(productId);
		return "Delete Successfull";
	}

	// Updating the product record
	public Products updateProduct(Long productId, Products updateProduct) {
		Products existingProduct = productsRepo.findById(productId)
				.orElseThrow(() -> new NoSuchElementException("Resource not present"));

		existingProduct.setProductName(updateProduct.getProductName());
		existingProduct.setProductPrice(updateProduct.getProductPrice());
		existingProduct.setProductQuantity(updateProduct.getProductQuantity());

		Category category = updateProduct.getCategory();
		if (category != null) {
			existingProduct.setCategory(category);
		}
		return productsRepo.save(existingProduct);
	}

	// Converting ProductEntity to ProductDto
	private ProductResponse mapProductEntityToProductResponseDto(Products product) {
		ProductResponse productResponse = new ProductResponse();
		productResponse.setProductId(product.getProductId());
		productResponse.setProductName(product.getProductName());
		productResponse.setProductPrice(product.getProductPrice());
		productResponse.setProductQuantity(product.getProductQuantity());
		productResponse.setCategoryName(mapCategoryEntityToCategoryDto(product));
		return productResponse;
	}

	// Converting CategoryEntity to CategoryDto
	private CategoryResponse mapCategoryEntityToCategoryDto(Products product) {
		CategoryResponse categoryResponse = new CategoryResponse();
		categoryResponse.setCategoryId(product.getCategory().getCategoryId());
		categoryResponse.setCategoryName(product.getCategory().getCategoryName());
		return categoryResponse;
	}
}
