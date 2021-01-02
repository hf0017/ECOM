package org.sid.billingservice.repositories;

import org.sid.billingservice.entities.ProductItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface ProductItemRepository extends JpaRepository<ProductItemEntity, Long>{

}
