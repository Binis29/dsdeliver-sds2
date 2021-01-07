package com.devsuperior.dsdeliver.services;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsdeliver.dto.OrderDto;
import com.devsuperior.dsdeliver.dto.ProductDto;
import com.devsuperior.dsdeliver.entities.Order;
import com.devsuperior.dsdeliver.entities.OrderStatus;
import com.devsuperior.dsdeliver.entities.Product;
import com.devsuperior.dsdeliver.repository.OrderRepository;
import com.devsuperior.dsdeliver.repository.ProductRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private ProductRepository productRepository;
		
	@Transactional(readOnly = true)
		public List<OrderDto> findAll(){
			List<Order> list = repository.findOrdersWithProducts();
			return list.stream().map(x -> new OrderDto(x))
					.collect(Collectors.toList());
			
			/* stream() transforms the elements of the list of products
			 * into streams and map map() applies a function to each element of the list making a new list. Thus, each element x of
			 * the type product is transformed in an element of type productdto by the arrow function map( x -> new ProductDto (x);
			 * The function collector() converts the stream back to a list;
			 */
		}
	
	@Transactional
	public OrderDto insert(OrderDto dto){
		Order order = new Order(null,dto.getAddress(),dto.getLatitude(),
				dto.getLongitude(),Instant.now(),OrderStatus.PENDING);
		for(ProductDto p : dto.getProducts()) {
			Product product = productRepository.getOne(p.getId());
			order.getProducts().add(product);
		}
		order = repository.save(order);
		return new OrderDto(order);
	}
	
	@Transactional
	public OrderDto setDelivered(Long id){
		Order order = repository.getOne(id);
		order.setStatus(OrderStatus.DELIVERED);
		order = repository.save(order);
		return new OrderDto(order);
	}
}
