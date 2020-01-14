package com.example.crud.service;

import java.util.List;

import com.example.crud.model.Product;

public interface ProductService {
	Product createProduct(Product product);
	Product updateProduct(Product product);
	List<Product> getAllProducts();
	Product getProductById(long productId);
	void deleteProduct(long productId);
}
