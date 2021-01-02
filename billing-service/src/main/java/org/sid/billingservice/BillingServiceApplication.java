package org.sid.billingservice;


import java.util.Date;

import org.sid.billingservice.entities.BillEntity;
import org.sid.billingservice.entities.Customer;
import org.sid.billingservice.entities.Product;
import org.sid.billingservice.entities.ProductItemEntity;
import org.sid.billingservice.repositories.BillRepository;
import org.sid.billingservice.repositories.ProductItemRepository;
import org.sid.billingservice.services.CustomerService;
import org.sid.billingservice.services.InventoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.PagedModel;


@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillingServiceApplication.class, args);
	}
	
	@Bean
	CommandLineRunner start(BillRepository billRepository, ProductItemRepository productItemRepository, 
			CustomerService customerService, InventoryService inventoryService) {
		return args -> {
			Customer c = customerService.findCustomerById(1L);
			System.out.println("ID:" + c.getId());
			System.out.println("Name:" + c.getName());
			System.out.println("Email:" + c.getEmail());
			System.out.println("************************");
			BillEntity bill = billRepository.save(new BillEntity(null, new Date(), c.getId(), null));
			
			PagedModel<Product> products = inventoryService.findAllProducts();
			products.getContent().forEach(p->{
				productItemRepository.save(new ProductItemEntity(null, p.getId(), p.getPrice(), 30, bill));
			});
		};
	}

}
