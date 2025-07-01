package com.kiit.FirstSpringboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kiit.FirstSpringboot.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>
{
	
	@Query(value = "select distinct product_category from product",nativeQuery = true)
	public List<String> getCategories();
	
	@Query(value = "select * from product where product_category=?1 and product_title=?2",nativeQuery = true)
	public List<Product> getProductsByCategoryAndTitle(String category,String title);
	
	

}
