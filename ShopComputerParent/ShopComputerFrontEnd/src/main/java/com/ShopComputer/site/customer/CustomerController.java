package com.ShopComputer.site.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ShopComputer.EntityCommon.Customer;

@Controller
public class CustomerController {
	
	@Autowired
	public CustomerService customerService;
	
	@GetMapping("/register")
	public String getFormRegister(Model model) {
		Customer customer= new Customer();
		model.addAttribute("customer", customer);
		return "register_form";
	}
	@PostMapping("/register")
	public String registerCustomer(Customer customer,Model model) {
		customerService.registerCustomer(customer);
		return "register_success";
		
	}
	

}
