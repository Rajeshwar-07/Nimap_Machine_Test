package com.ecommerce.service;

import java.util.List;

import com.ecommerce.entity.Products;
import com.ecommerce.responseDTO.ProductResponse;

public interface ProductService {

	Products saveProduct(Products products, Long categoryId);
	
	List<ProductResponse> getAllProducts(Integer pageSize);
	
	ProductResponse getProductById(Long productId);
	
	Products updateProduct(Long productId, Products updateProduct);
	
	String deleteProduct(Long productId);
}
