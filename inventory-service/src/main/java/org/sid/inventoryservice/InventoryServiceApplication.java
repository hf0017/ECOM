package org.sid.inventoryservice;

import org.sid.inventoryservice.entities.ProductEntity;
import org.sid.inventoryservice.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(ProductRepository productRepository, RepositoryRestConfiguration restConfiguration) {
		return args -> {
			restConfiguration.exposeIdsFor(ProductEntity.class);
			productRepository.save(new ProductEntity(null, "macbook pro", 20000));
			productRepository.save(new ProductEntity(null, "macbook air", 10000));
			productRepository.findAll().forEach(System.out::println);
		};
	}
}
