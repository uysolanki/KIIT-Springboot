package com.kiit.FirstSpringboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiit.FirstSpringboot.model.Product;
import com.kiit.FirstSpringboot.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;
	
	public Product addProduct(Product product) {
		return productRepository.save(product);
	}

	public Product getProduct(int id) {
		return productRepository.findById(id).get();
	}

	public List<Product> getProducts() {
		return productRepository.findAll();
		
	}

	public List<Product> addMultipleProductsByRequestBody(List<Product> products) {
		return productRepository.saveAll(products);
	}

	public Product updateProduct(int prodId, Product newValue) {
		Product productFromDb=getProduct(prodId);
		productFromDb.setProductCategory(newValue.getProductCategory());
		productFromDb.setProductDescription(newValue.getProductDescription());
		productFromDb.setProductPrice(newValue.getProductPrice());
		productFromDb.setProductTitle(newValue.getProductTitle());
		return productRepository.save(productFromDb);
	}

	public void deleteProduct(int prodId) {
		productRepository.deleteById(prodId);
	}

}
