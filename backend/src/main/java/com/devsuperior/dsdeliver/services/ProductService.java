package com.devsuperior.dsdeliver.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsdeliver.dto.ProductDto;
import com.devsuperior.dsdeliver.entities.Product;
import com.devsuperior.dsdeliver.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
		
	@Transactional(readOnly = true)
		public List<ProductDto> findAll(){
			List<Product> list = repository.findAllByOrderByNameAsc();
			return list.stream().map(x -> new ProductDto(x)).collect(Collectors.toList());
			
			/* stream() transforms the elements of the list of products
			 * into streams and map map() applies a function to each element of the list making a new list. Thus, each element x of
			 * the type product is transformed in an element of type productdto by the arrow function map( x -> new ProductDto (x);
			 * The function collector() converts the stream back to a list;
			 */
		}
}
