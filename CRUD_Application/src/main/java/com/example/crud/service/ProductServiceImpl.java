package com.example.crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.crud.exception.ResourceNotFoundException;
import com.example.crud.model.Product;
import com.example.crud.repository.ProductRepository;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public Product createProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product updateProduct(Product product) {
		
		Optional<Product> productDb = this.productRepository.findById(product.getId());
		
		if(productDb.isPresent()) {
			Product productUpdate = productDb.get();
			productUpdate.setId(product.getId());
			productUpdate.setName(product.getName());
			productUpdate.setDescription(product.getDescription());
			productUpdate.setPrice(product.getPrice());
			productRepository.save(productUpdate);
			return productUpdate;
		}else {
			throw new ResourceNotFoundException("Record not found with the mentioned Id :"+product.getId());
		}
	}

	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product getProductById(long productId) {
		
		Optional<Product> product = productRepository.findById(productId);
		
		if(product.isPresent()) {
			return product.get();
		}else {
			throw new ResourceNotFoundException("Product not found for given Id : "+productId);
		}
	}

	@Override
	public void deleteProduct(long productId) {
		
		Optional<Product> product = productRepository.findById(productId);
		if(product.isPresent()) {
			productRepository.deleteById(productId);
		}else {
			throw new ResourceNotFoundException("Product not found for given Id : "+productId);
		}
	}

}
