package com.ShopComputer.site.customer;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ShopComputer.EntityCommon.Customer;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public Customer findById(Long id) {
		return customerRepository.findById(id).get();
	}
	
	public void registerCustomer(Customer customer) {
		customer.setCreateTime(new Date());
		customer.setEnable(false);
		customer.setVerificationCode("123456");
		customerRepository.save(customer);
	}

}
