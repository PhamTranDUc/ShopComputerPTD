package com.ShopComputer.admin.customer;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ShopComputer.EntityCommon.Customer;

@Service
@Transactional
public class CustomerService {

	@Autowired
	public CustomerRepository customerRepository;
	
	public Page<Customer> getByPage(int page,String sortBy,String sortType,String key){
		Sort sort = Sort.by(sortBy);
		sort= sortType.equals("asc")?sort.ascending(): sort.descending();
		Pageable pageable = PageRequest.of(page-1, 5,sort);
		Page<Customer> pageRs= customerRepository.findAll(pageable);		
		return pageRs;
	}
	
	public Customer getById(Long id) {
		Optional<Customer> cusOptional= customerRepository.findById(id);
		if(cusOptional.isPresent()) {
			return cusOptional.get();
		}
		return null;
	}
	
	public void changeEnable(Long id) {
		Optional<Customer> optional= customerRepository.findById(id);
		if(optional.isPresent()) {
			Customer customer= optional.get();
			boolean check= customer.getEnable();
			if(check) {
				customerRepository.setDisable(id);
			}else {
				customerRepository.setEnable(id);
			}
		}
		
	}
	
	public void saveCustomer(Customer c) {
		Customer customer = customerRepository.findById(c.getId()).get();
		customer.setAddress1(c.getAddress1());
		customer.setAddress2(c.getAddress2());
		customer.setEnable(c.getEnable());
		customer.setFirstName(c.getFirstName());
		customer.setLastName(c.getLastName());
		customer.setPhoneNumber(c.getPhoneNumber());
		customerRepository.save(customer);
	}
	
}
