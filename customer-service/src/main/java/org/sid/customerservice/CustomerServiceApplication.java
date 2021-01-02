package org.sid.customerservice;


import org.sid.customerservice.entities.CustomerEntity;
import org.sid.customerservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;


@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}
	
	@Bean
	CommandLineRunner start(CustomerRepository customerRepository, RepositoryRestConfiguration restConfiguration) {
		return args -> {
			restConfiguration.exposeIdsFor(CustomerEntity.class);
			customerRepository.save(new CustomerEntity(null, "hamza", "hamza@gmail.com"));
			customerRepository.save(new CustomerEntity(null, "haitam", "haitam@gmail.com"));
			customerRepository.findAll().forEach(System.out::println);
		};
	}

}
