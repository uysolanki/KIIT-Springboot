package com.kiit.FirstSpringboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

	public List<String> getCategories() {
		return productRepository.getCategories();	
	}

	public List<Product> getProductByCategoryAndTitle(String category, String title) {
		//return productRepository.getProductsByCategoryAndTitle(category, title);
		return productRepository.findByProductCategoryAndProductTitle(category, title);
	}

	public List<Product> searchProduct(String searchString) {
		return productRepository.findByProductDescriptionContaining(searchString);
	}

	public Page<Product> getProductByPagination(int pageNumber, int pageSize) {
		return productRepository.findAll(PageRequest.of(pageNumber, pageSize));
	}

	public Page<Product> getProductBySortingAndPagination(String sortingField, int pageNumber, int pageSize) {
		return productRepository.findAll(PageRequest.of(pageNumber, pageSize).withSort(Sort.by(Sort.Direction.ASC,sortingField)));
	}

}
