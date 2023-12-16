package com.ShopComputer.admin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.ShopComputer.admin.customer.CustomerRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class testCustomerRepository {

	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Test
	public void testEnable() {
		customerRepository.setEnable(1L);
	}
	
	@Test
	public void testDisable() {
		customerRepository.setDisable(1L);
	}
}
