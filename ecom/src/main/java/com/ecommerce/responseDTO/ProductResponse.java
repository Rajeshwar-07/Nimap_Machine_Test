package com.ecommerce.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
	
	private Long productId;

	private String productName;
	
	private String productPrice;
	
	private String productQuantity;
	
	private CategoryResponse categoryName;

}
