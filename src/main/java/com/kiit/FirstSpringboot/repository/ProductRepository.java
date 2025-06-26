package com.kiit.FirstSpringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.kiit.FirstSpringboot.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>
{

}
