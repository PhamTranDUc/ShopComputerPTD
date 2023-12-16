package com.ShopComputer.site.customer;

import java.util.Date;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ShopComputer.EntityCommon.Customer;

import net.bytebuddy.utility.RandomString;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Service
@Transactional
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
	
//	public boolean verifyCustomer(String code) {
//		Optional<Customer> customer = customerRepository.findByVerificationCode(code);
//		if(customer.isEmpty() || customer.get().enable == true) {
//			return false;
//		}
//		else {					
//			customerRepository.verifyCustomer(code);
//			customer.get().setVerificationCode(null);
//			customerRepository.save(customer.get());
//			return true;
//		}
//		
//	}
	public boolean verifyCustomer(String code) {
	    Optional<Customer> customerOptional = customerRepository.findByVerificationCode(code);

	    // Kiểm tra xem customer có tồn tại và enable là false
	    if (customerOptional.isPresent() && !customerOptional.get().getEnable()) {
	       customerRepository.verifyCustomer(code);
	        return true;
	    }

	    return false;
	}
	
	
	

}
