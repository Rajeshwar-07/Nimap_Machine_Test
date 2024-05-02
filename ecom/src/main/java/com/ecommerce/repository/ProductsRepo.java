package com.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.entity.Products;
import com.ecommerce.responseDTO.ProductResponse;

public interface ProductsRepo extends JpaRepository<Products, Long>{

	Products save(ProductResponse product);

}
