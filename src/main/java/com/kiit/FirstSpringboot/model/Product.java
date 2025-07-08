package com.kiit.FirstSpringboot.model;

import java.time.LocalDateTime;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
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
@EntityListeners(AuditingEntityListener.class)
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int productId;
	
	@NotNull(message = "Product Title cannot be null") 
	@Size(min = 3, message = "Product Title must contain at least 3 characters") 
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
	
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
	
	@PrePersist
	protected void atCreation()
	{
		LocalDateTime now=LocalDateTime.now();
		this.createdAt=now;
		this.modifiedAt=now;
	}
	
	@PreUpdate
	protected void atUpdation()
	{
		this.modifiedAt=LocalDateTime.now();
	}

}

//[
//{price=90
//message: Product Price must be at least 100
//field : productPrice
//rejectedValue : 90
//},

//{
//title : ab
//message: Product Title must contain at least 3 characters
//field : productTitle
//rejectedValue : Ab
//}
//]
