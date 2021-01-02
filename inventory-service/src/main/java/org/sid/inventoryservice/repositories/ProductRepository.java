package org.sid.inventoryservice.repositories;

import org.sid.inventoryservice.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

}
