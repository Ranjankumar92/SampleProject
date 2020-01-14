package com.example.crud.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.example.crud.model.Product;
import com.example.crud.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProducts(){
		return ResponseEntity.ok(productService.getAllProducts());
	}
	
	@GetMapping("/productById/{Id}")
	public ResponseEntity<Product> getProductById(@PathVariable long Id){
		return ResponseEntity.ok(productService.getProductById(Id));
	}

	@PostMapping("/saveProduct")
	public ResponseEntity<Product> createProduct(@RequestBody Product product){
		return ResponseEntity.ok(productService.createProduct(product));
	}
	
	@PutMapping("/updateProducts/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable long id, @RequestBody Product product){
		product.setId(id);
		return ResponseEntity.ok(productService.updateProduct(product));
	}
	
	@DeleteMapping("/deleteProduct/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable long id) {
		this.productService.deleteProduct(id);
		return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.OK);
	}
}
