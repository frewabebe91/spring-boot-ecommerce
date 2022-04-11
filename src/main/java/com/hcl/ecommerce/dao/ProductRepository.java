package com.hcl.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.hcl.ecommerce.entity.Product;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Pageable; 
import org.springframework.data.domain.Page;

@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	Page<Product> findByCategoryId(@RequestParam("id") Long id, Pageable pageable);
	
	Page<Product> findByNameContaining(@RequestParam("name") String name, Pageable pageable);
	

}

