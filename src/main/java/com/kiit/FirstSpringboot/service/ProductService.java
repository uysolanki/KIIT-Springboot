package com.kiit.FirstSpringboot.service;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kiit.FirstSpringboot.model.Product;
import com.kiit.FirstSpringboot.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;
	
	private static final Logger logger=Logger.getLogger(ProductService.class);
	
	public Product addProduct(Product product) {
		logger.info("Service Method for Add Product API Request for Product" +product.getProductId());
		return productRepository.save(product);
	}

	public Product getProduct(int id) {
		return productRepository.findById(id).get();
	}

	public List<Product> getProducts() {
		return productRepository.findAll();
		
	}

	@Transactional(rollbackFor = ConstraintViolationException.class)
	public List<Product> addMultipleProductsByRequestBody(List<Product> products) {
		List<Product> products1=null;
		try
		{
		products1= productRepository.saveAll(products);
		productRepository.flush();
		}
		catch(Exception e) {
			System.err.println(e.getClass());
			throw e;
		}
		return products1;
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
