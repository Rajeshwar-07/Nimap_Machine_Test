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

import com.ecommerce.entity.Products;
import com.ecommerce.responseDTO.ProductResponse;
import com.ecommerce.service.implementation.ProductServiceImplementation;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductServiceImplementation productServiceimpl;

	@PostMapping("/{categoryId}")
	public ResponseEntity<Products> saveProduct(@RequestBody Products products, @PathVariable Long categoryId) {
		Products savedProduct = productServiceimpl.saveProduct(products, categoryId);
		return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<ProductResponse>> getAll(
			@RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize) {
		List<ProductResponse> productList = productServiceimpl.getAllProducts(pageSize);
		return ResponseEntity.ok(productList);
	}

	@GetMapping("/{productId}")
	public ResponseEntity<ProductResponse> getProductById(@PathVariable Long productId) {
		ProductResponse ProductResponse = productServiceimpl.getProductById(productId);
		return ResponseEntity.ok(ProductResponse);
	}

	@DeleteMapping("/{productId}")
	public String deleteProduct(@PathVariable Long productId) {
		if (productId == null) {
			throw new RuntimeException("Resource Not found Exception");
		}
		productServiceimpl.deleteProduct(productId);
		return "Product delete successfull";
	}

	@PutMapping("/{productId}")
	public ResponseEntity<Products> updateProduct(@PathVariable Long productId, @RequestBody Products product) {
		Products updateProducts = productServiceimpl.updateProduct(productId, product);
		return new ResponseEntity<>(updateProducts, HttpStatus.CREATED);
	}

}
