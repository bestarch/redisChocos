package com.bestarch.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

	private String productId;
	private String productName;
	private String location;
	private String sku;
	private String description;
	private Double price;
	private String category;
}