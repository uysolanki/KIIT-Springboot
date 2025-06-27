package com.kiit.FirstSpringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kiit.FirstSpringboot.model.Product;
import com.kiit.FirstSpringboot.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	ProductService productService;

	@RequestMapping("/test")
	public String test(Model model)
	{
		Product product=new Product();
		product.setProductCategory("Electronics");
		product.setProductDescription("Dell Inspiron 15 Laptop");
		product.setProductId(1);
		product.setProductPrice(300.0);
		product.setProductTitle("Laptop");
		model.addAttribute("product",product);
		return "Test";
	}
	
	@PostMapping("/addProduct")
	public String addProduct()
	{
		Product product=new Product();
		product.setProductCategory("Electronics");
		product.setProductDescription("Dell Inspiron 15 Laptop");
		product.setProductId(1);
		product.setProductPrice(300.0);
		product.setProductTitle("Laptop");
		productService.addProduct(product);
		return "Product Addedd Successfully";
	}
	
	@RequestMapping("/addProduct1")
	public String addProduct1()
	{
		Product product=Product.builder()
				.productCategory("Jewelry")
				.productPrice(100.0)
				.productTitle("Gold Ring")
				.build();
		productService.addProduct(product);
		return "Product Addedd Successfully";
	}
	
	@GetMapping("/getProduct")
	public Product getProduct()
	{
		return productService.getProduct(1);
	}
	
	@PostMapping("/addProductByRequestParam")
	public String addProductByRequestParam(@RequestParam("ptitle") String title,
										   @RequestParam("pdesc") String description,
										   @RequestParam("pcategory") String category,
										   @RequestParam("prpice") double price)
	{
		Product product=Product.builder()
				.productCategory(category)
				.productPrice(price)
				.productTitle(title)
				.productDescription(description)
				.build();
		productService.addProduct(product);
		return "Product Addedd Successfully";
	}
	
	
	@PostMapping("/addProductByPathVariable/{a}/{b}/{c}/{d}")
	public String addProductByPathVariable(@PathVariable("a") String title,
										   @PathVariable("b") String description,
										   @PathVariable("c") String category,
										   @PathVariable("d") double price)
	{
		Product product=Product.builder()
				.productCategory(category)
				.productPrice(price)
				.productTitle(title)
				.productDescription(description)
				.build();
		productService.addProduct(product);
		return "Product Addedd Successfully";
	}
	
}
