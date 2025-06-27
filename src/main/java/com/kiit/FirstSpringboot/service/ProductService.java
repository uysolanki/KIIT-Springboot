package com.kiit.FirstSpringboot.service;

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

}
