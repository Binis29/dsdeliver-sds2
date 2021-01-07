package com.devsuperior.dsdeliver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsdeliver.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	/*By default, findAll is a method of the JPA library and does not need to be declared, however, if
	 * I want a different way of data searching, it must be implemented in a different method */
	List<Product> findAllByOrderByNameAsc();
}
