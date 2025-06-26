package com.kiit.FirstSpringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kiit.FirstSpringboot.model.Product;

@Controller
public class ProductController {

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
}
