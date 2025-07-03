//package com.kiit.FirstSpringboot.controller;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.validation.Valid;
//
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.kiit.FirstSpringboot.model.Product;
//import com.kiit.FirstSpringboot.service.ProductService;
//import com.kiit.FirstSpringboot.util.APIError;
//
//@RestController
//public class ProductController {
//	
//	@Autowired
//	ProductService productService;
//	
//	private static final Logger logger=Logger.getLogger(ProductController.class);
//
//	@RequestMapping("/test")
//	public String test(Model model)
//	{
//		Product product=new Product();
//		product.setProductCategory("Electronics");
//		product.setProductDescription("Dell Inspiron 15 Laptop");
//		product.setProductId(1);
//		product.setProductPrice(300.0);
//		product.setProductTitle("Laptop");
//		model.addAttribute("product",product);
//		return "Test";
//	}
//	
//	@PostMapping("/addProduct")
//	public String addProduct()
//	{
//		Product product=new Product();
//		product.setProductCategory("Electronics");
//		product.setProductDescription("Dell Inspiron 15 Laptop");
//		product.setProductId(1);
//		product.setProductPrice(300.0);
//		product.setProductTitle("Laptop");
//		productService.addProduct(product);
//		return "Product Addedd Successfully";
//	}
//	
//	@RequestMapping("/addProduct1")
//	public String addProduct1()
//	{
//		Product product=Product.builder()
//				.productCategory("Jewelry")
//				.productPrice(100.0)
//				.productTitle("Gold Ring")
//				.build();
//		productService.addProduct(product);
//		return "Product Addedd Successfully";
//	}
//	
////	@GetMapping("/getProduct")
////	public Product getProduct()
////	{
////		return productService.getProduct(1);
////	}
//	
//	@PostMapping("/addProductByRequestParam")
//	public String addProductByRequestParam(@RequestParam("ptitle") String title,
//										   @RequestParam("pdesc") String description,
//										   @RequestParam("pcategory") String category,
//										   @RequestParam("prpice") double price)
//	{
//		Product product=Product.builder()
//				.productCategory(category)
//				.productPrice(price)
//				.productTitle(title)
//				.productDescription(description)
//				.build();
//		productService.addProduct(product);
//		return "Product Addedd Successfully";
//	}
//	
//	
//	@PostMapping("/addProductByPathVariable/{a}/{b}/{c}/{d}")
//	public String addProductByPathVariable(@PathVariable("a") String title,
//										   @PathVariable("b") String description,
//										   @PathVariable("c") String category,
//										   @PathVariable("d") double price)
//	{
//		Product product=Product.builder()
//				.productCategory(category)
//				.productPrice(price)
//				.productTitle(title)
//				.productDescription(description)
//				.build();
//		productService.addProduct(product);
//		return "Product Addedd Successfully";
//	}
//	
//	@PostMapping("/addProductByPathVariable1/{title}/{description}/{category}/{price}")
//	public Product addProductByPathVariable1(@PathVariable String title,
//										   @PathVariable String description,
//										   @PathVariable String category,
//										   @PathVariable double price)
//	{
//		Product product=Product.builder()
//				.productCategory(category)
//				.productPrice(price)
//				.productTitle(title)
//				.productDescription(description)
//				.build();
//		return productService.addProduct(product);
//	}
//	
//	@PostMapping("/addProductByRequestBody")
//	public ResponseEntity<?> addProductByRequestBody(@RequestBody @Valid Product product,BindingResult bindingResult)
//	{
//		logger.info("Controller Method for Add Product API Request for Product" +product.getProductId());
//		if(bindingResult.hasErrors())
//		{
//			List<APIError> errors = new ArrayList<>();
//			for(FieldError error : bindingResult.getFieldErrors())
//			{
//				APIError error1=new APIError(error.getDefaultMessage(), error.getField(), error.getRejectedValue());
//				errors.add(error1);
//			}
//			return new  ResponseEntity<List<APIError>>(errors,HttpStatus.BAD_REQUEST);
//		}
//		Product product1=productService.addProduct(product);
//		logger.info("Product Added Successfuly" +product.getProductId());
//		return new ResponseEntity<Product>(product1,HttpStatus.CREATED);
//	}
//	
//	@PostMapping("/addMultipleProductsByRequestBody")
//	public ResponseEntity<List<Product>> addMultipleProductsByRequestBody(@RequestBody List<Product> products)
//	{
//		List<Product> products1=productService.addMultipleProductsByRequestBody(products);
//		return new ResponseEntity<List<Product>>(products1,HttpStatus.CREATED);
//	}
//	
//	
//	@GetMapping("/getProducts")
//	public List<Product> getProducts()
//	{
//		List<Product> products=productService.getProducts();
//		return products;
//	}
//	
//	@GetMapping("/getProduct")
//	ResponseEntity<Product> getProduct(@RequestParam("pno") int pno)
//	{
//		Product product=productService.getProduct(pno);
//		return new ResponseEntity<Product>(product,HttpStatus.OK);
//	}
//	
//	@GetMapping("/getProduct1/{pno}")
//	ResponseEntity<Product> getProduct1(@PathVariable int pno)
//	{
//		Product product=productService.getProduct(pno);
//		return new ResponseEntity<Product>(product,HttpStatus.OK);
//	}
//	
//	
//	@PutMapping("/updateProduct/{prodId}")
//	public ResponseEntity<Product> updateProduct(@PathVariable int prodId, @RequestBody Product newValue )
//	{
//		return  new ResponseEntity<Product>(productService.updateProduct(prodId,newValue),HttpStatus.OK) ;
//	}
//	
//	@DeleteMapping("/deleteProduct/{prodId}")
//	public ResponseEntity<String> deleteProduct(@PathVariable int prodId)
//	{
//		productService.deleteProduct(prodId);
//		return  new ResponseEntity<String>("Product Deleted with ID "+prodId ,HttpStatus.OK) ;
//	}
//	
//	@GetMapping("/getCategories")
//	ResponseEntity<List<String>> getCategories()
//	{
//		List<String> categories=productService.getCategories();
//		return new ResponseEntity<List<String>>(categories,HttpStatus.OK);
//	}
//	
//	@GetMapping("/getProductByCategoryAndTitle/{category}/{title}")
//	ResponseEntity<List<Product>> getProductByCategoryAndTitle(@PathVariable String category, @PathVariable String title)
//	{
//		List<Product> productList=productService.getProductByCategoryAndTitle(category,title);
//		return new ResponseEntity<List<Product>>(productList,HttpStatus.OK);
//	}
//	
//	@GetMapping("/searchProduct/{searchString}")
//	ResponseEntity<List<Product>> searchProduct(@PathVariable String searchString)
//	{
//		List<Product> productList=productService.searchProduct(searchString);
//		return new ResponseEntity<List<Product>>(productList,HttpStatus.OK);
//	}
//	
//	@GetMapping("/getProductByPagination/{pageNumber}/{pageSize}")
//	ResponseEntity<Page<Product>> getProductByPagination(@PathVariable int pageNumber,@PathVariable int pageSize)
//	{
//		Page<Product> productPage=productService.getProductByPagination(pageNumber,pageSize);
//		return new ResponseEntity<Page<Product>>(productPage,HttpStatus.OK);
//	}
//	
//
//	@GetMapping("/getProductBySortingAndPagination/{sortingField}/{pageNumber}/{pageSize}")
//	ResponseEntity<Page<Product>> getProductBySortingAndPagination(@PathVariable int pageNumber,@PathVariable int pageSize,@PathVariable String sortingField)
//	{
//		Page<Product> productPage=productService.getProductBySortingAndPagination(sortingField,pageNumber,pageSize);
//		return new ResponseEntity<Page<Product>>(productPage,HttpStatus.OK);
//	}
//}
//
///*
// {
//    "productTitle": "Ring",
//    "productDescription": "Made from 24Karat Gold",
//    "productCategory": "Jewelry",
//    "productPrice": 1500.0
// }
// 
// 
// [
//    {
//        "productTitle": "Portable HDD",
//        "productDescription": "WD 2TB Portable HDD",
//        "productCategory": "Electronics",
//        "productPrice": 300.0
//    },
//    {
//       "productTitle": "Portable HDD",
//        "productDescription": "Sandisk 2TB Portable HDD",
//        "productCategory": "Electronics",
//        "productPrice": 350.0
//    },
//    {
//       "productTitle": "TV",
//        "productDescription": "56 inch LED TV Sony",
//        "productCategory": "Electronics",
//        "productPrice": 1500.0
//    }
//    
// ]
// */
//
//
