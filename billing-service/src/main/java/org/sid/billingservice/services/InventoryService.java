package org.sid.billingservice.services;


import org.sid.billingservice.entities.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "INVENTORY-SERVICE")
public interface InventoryService {

	@GetMapping("/productEntities/{id}")
	public Product findProductById(@PathVariable(name="id") Long id);
	@GetMapping("/productEntities")
	public PagedModel<Product> findAllProducts();
}
