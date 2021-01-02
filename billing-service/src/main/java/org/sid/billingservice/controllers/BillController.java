package org.sid.billingservice.controllers;

import org.sid.billingservice.entities.BillEntity;
import org.sid.billingservice.repositories.BillRepository;
import org.sid.billingservice.services.CustomerService;
import org.sid.billingservice.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class BillController {
	
	@Autowired
	private BillRepository billRepository; 
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private InventoryService inventoryService;
	
	
	@GetMapping("/fullBill/{id}")
	public BillEntity getBillEntity(@PathVariable(name="id") Long id) {
		BillEntity bill = billRepository.findById(id).get();
		bill.setCustomer(customerService.findCustomerById(bill.getCustumerId()));
		bill.getProductItems().forEach(pi->{
			pi.setProduct(inventoryService.findProductById(pi.getProductId()));
		});
		return bill;
	}

}
