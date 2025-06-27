package com.kiit.FirstSpringboot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int productId;
	
	@NotNull(message = "Product Title cannot be null") 
	@Size(min = 3, message = "First name must contain at least 3 characters") 
	String productTitle;
	
	@NotNull(message = "Product Description cannot be null") 
	String productDescription;
	
	@NotNull(message = "Product Title cannot be null")
	String productCategory;
	@NotNull(message = "Product Price cannot be null") 
	@Min(value = 100, message = "Product Price must be at least 100") 
	@Max(value = 5000, message = "Product Price must be less than or equal to 5000") 
	@Positive
	double productPrice;
}
