package com.ShopComputer.site.customer;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ShopComputer.EntityCommon.Customer;

import net.bytebuddy.utility.RandomString;

@Service
public class CustomerService {
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public Customer findById(Long id) {
		return customerRepository.findById(id).get();
	}
	
	public void registerCustomer(Customer customer) {
		customer.setCreateTime(new Date());
		customer.setEnable(false);
		String passWord= passwordEncoder.encode(customer.getPassWord());
		customer.setPassWord(passWord);
		String verificationCode= RandomString.make(64);
		customer.setVerificationCode(verificationCode);
		customerRepository.save(customer);
	}

}
