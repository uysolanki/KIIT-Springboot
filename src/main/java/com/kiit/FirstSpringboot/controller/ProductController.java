package com.kiit.FirstSpringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductController {

	@RequestMapping("/test")
	public String test()
	{
		return "Test";
	}
}
