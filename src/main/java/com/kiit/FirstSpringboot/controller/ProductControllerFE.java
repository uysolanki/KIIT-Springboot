package com.kiit.FirstSpringboot.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kiit.FirstSpringboot.model.Product;
import com.kiit.FirstSpringboot.service.ProductService;
import com.kiit.FirstSpringboot.util.APIError;

@Controller
public class ProductControllerFE {
	
	@Autowired
	ProductService productService;
	
	private static final Logger logger=Logger.getLogger(ProductControllerFE.class);

	@RequestMapping("/")
	public String showProducts(Model model)
	{

	List<Product> products=productService.getProducts();
	List<String> categories=productService.getCategories();
	categories.add("All");
	model.addAttribute("products", products);
	model.addAttribute("categories",categories);
	return "AllProducts";   //html file - name of the html file
	}
	
	@RequestMapping("/searchProducts")
	public String searchProducts(@RequestParam("title") String title,Model model)
	{

	List<Product> products=productService.getProducts();
	List<Product> selectedProducts=new ArrayList();
	
	for(Product product:products)
	{
		if(product.getProductTitle().toLowerCase().contains(title.toLowerCase()))
		{
			selectedProducts.add(product);
		}
	}
	model.addAttribute("products",selectedProducts);
	List<String> categories=productService.getCategories();
	categories.add("All");
	model.addAttribute("categories",categories);
	return "AllProducts";   //html file - name of the html file
	}
	
	@RequestMapping("/filterProducts")
	public String filterProducts(@RequestParam("category") String category,Model model)
	{

	List<Product> products=productService.getProducts();
	List<Product> filteredProducts=new ArrayList();
	
	if(category.equalsIgnoreCase("all"))
	{
		model.addAttribute("products",products);
		List<String> categories=productService.getCategories();
		categories.add("All");
		model.addAttribute("products",products);
		model.addAttribute("categories",categories);
		return "AllProducts";
	}
	
	for(Product product:products)
	{
		if(product.getProductCategory().toLowerCase().contains(category.toLowerCase()))
		{
			filteredProducts.add(product);
		}
	}
	model.addAttribute("products",filteredProducts);
	List<String> categories=productService.getCategories();
	categories.add("All");
	model.addAttribute("categories",categories);
	return "AllProducts";   //html file - name of the html file
	}
	
	@RequestMapping("/addProductForm")
	public String addProductForm(Model model)
	{

	Product product = new Product();
	model.addAttribute("product", product);
	return "AddProductForm";
	}

	
	@PostMapping("/addProductByRequestBody")
	public String addProductByRequestBody(@ModelAttribute @Valid Product product,BindingResult bindingResult)
	{
		logger.info("Controller Method for Add Product API Request for Product" +product.getProductId());
		if(bindingResult.hasErrors())
		{
			List<APIError> errors = new ArrayList<>();
			for(FieldError error : bindingResult.getFieldErrors())
			{
				APIError error1=new APIError(error.getDefaultMessage(), error.getField(), error.getRejectedValue());
				errors.add(error1);
			}
			return "Error-Page";
		}
		Product product1=productService.addProduct(product);
		logger.info("Product Added Successfuly" +product.getProductId());
		return "redirect:/";  //endpoint   - redirect: endpoint name
	}
	
	
	@RequestMapping("/deleteProduct/{prodId}")
	public String deleteProduct(@PathVariable int prodId)
	{
		productService.deleteProduct(prodId);
		return "redirect:/";
	}
	
	@RequestMapping("/updateProductForm/{prodId}")
	public String updateProductForm(@PathVariable int prodId, Model model)
	{
		Product product=productService.getProduct(prodId);
		model.addAttribute("product",product);
		return "UpdateProductForm";
	}
	
	
	@PostMapping("/updateProduct/{prodId}")
	public String updateProduct(@PathVariable int prodId, @ModelAttribute Product newValue )
	{
		productService.updateProduct(prodId,newValue) ;
		return "redirect:/";
	}
	
	
	
	@GetMapping("/getCategories")
	ResponseEntity<List<String>> getCategories()
	{
		List<String> categories=productService.getCategories();
		return new ResponseEntity<List<String>>(categories,HttpStatus.OK);
	}
	
	@GetMapping("/getProductByCategoryAndTitle/{category}/{title}")
	ResponseEntity<List<Product>> getProductByCategoryAndTitle(@PathVariable String category, @PathVariable String title)
	{
		List<Product> productList=productService.getProductByCategoryAndTitle(category,title);
		return new ResponseEntity<List<Product>>(productList,HttpStatus.OK);
	}
	
	@GetMapping("/searchProduct/{searchString}")
	ResponseEntity<List<Product>> searchProduct(@PathVariable String searchString)
	{
		List<Product> productList=productService.searchProduct(searchString);
		return new ResponseEntity<List<Product>>(productList,HttpStatus.OK);
	}
	
	@GetMapping("/getProductByPagination/{pageNumber}/{pageSize}")
	ResponseEntity<Page<Product>> getProductByPagination(@PathVariable int pageNumber,@PathVariable int pageSize)
	{
		Page<Product> productPage=productService.getProductByPagination(pageNumber,pageSize);
		return new ResponseEntity<Page<Product>>(productPage,HttpStatus.OK);
	}
	

	@GetMapping("/getProductBySortingAndPagination/{sortingField}/{pageNumber}/{pageSize}")
	ResponseEntity<Page<Product>> getProductBySortingAndPagination(@PathVariable int pageNumber,@PathVariable int pageSize,@PathVariable String sortingField)
	{
		Page<Product> productPage=productService.getProductBySortingAndPagination(sortingField,pageNumber,pageSize);
		return new ResponseEntity<Page<Product>>(productPage,HttpStatus.OK);
	}
	
	@RequestMapping("/403")
	public ModelAndView accesssDenied(Principal user) {

		ModelAndView model = new ModelAndView();

		if (user != null) {
			model.addObject("msg", "Hi " + user.getName() 
			+ ", you do not have permission to access this page!");
		} else {
			model.addObject("msg", 
			    "you do not have permission to access this page!");
		}

		model.setViewName("403");
		return model;

	}

}

/*
 {
    "productTitle": "Ring",
    "productDescription": "Made from 24Karat Gold",
    "productCategory": "Jewelry",
    "productPrice": 1500.0
 }
 
 
 [
    {
        "productTitle": "Portable HDD",
        "productDescription": "WD 2TB Portable HDD",
        "productCategory": "Electronics",
        "productPrice": 300.0
    },
    {
       "productTitle": "Portable HDD",
        "productDescription": "Sandisk 2TB Portable HDD",
        "productCategory": "Electronics",
        "productPrice": 350.0
    },
    {
       "productTitle": "TV",
        "productDescription": "56 inch LED TV Sony",
        "productCategory": "Electronics",
        "productPrice": 1500.0
    }
    
 ]
 */


