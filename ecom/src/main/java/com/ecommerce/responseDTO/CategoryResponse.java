package com.ecommerce.responseDTO;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse {
	
	private Long categoryId;
	private String CategoryName;	
}
