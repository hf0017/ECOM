package org.sid.billingservice.entities;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class BillEntity {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date billingDate;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Long custumerId;
	@Transient
	private Customer customer;
	@OneToMany(mappedBy = "bill")
	private Collection<ProductItemEntity> productItems;
	
	
	
	public BillEntity(Long id, Date billingDate, Long custumerId, Collection<ProductItemEntity> productItems) {
		super();
		this.id = id;
		this.billingDate = billingDate;
		this.custumerId = custumerId;
		this.productItems = productItems;
	}
	
	public BillEntity() {
		super();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getBillingDate() {
		return billingDate;
	}
	public void setBillingDate(Date billingDate) {
		this.billingDate = billingDate;
	}
	public Long getCustumerId() {
		return custumerId;
	}
	public void setCustumerId(Long custumerId) {
		this.custumerId = custumerId;
	}
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Collection<ProductItemEntity> getProductItems() {
		return productItems;
	}
	public void setProductItems(Collection<ProductItemEntity> productItems) {
		this.productItems = productItems;
	}
	
	
	
	
}
