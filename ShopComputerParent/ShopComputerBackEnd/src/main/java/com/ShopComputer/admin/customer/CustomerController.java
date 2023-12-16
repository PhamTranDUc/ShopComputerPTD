package com.ShopComputer.admin.customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ShopComputer.EntityCommon.Customer;
@Controller
public class CustomerController {
	@Autowired
	public CustomerService customerService;
	
	@GetMapping("/customer")
	public String getAllCustomer(Model model) {
		return getAllCustomerByPage(1,"id","asc",model);
	}
	
	@GetMapping("/customer/page/{currentPage}")
	public String getAllCustomerByPage(@PathVariable ("currentPage") int page, @Param("sortBy") String sortBy, @Param("sortType") String sortType, Model model) {
		Page<Customer> pageRs= customerService.getByPage(page, sortBy, sortType, sortType);
		List<Customer> listRs= new ArrayList<>();
		listRs.addAll(pageRs.getContent());
		model.addAttribute("listCustomer", listRs);
		String sortRever= sortType.equals("asc")?"des":"asc";
		model.addAttribute("sortRever", sortRever);
		model.addAttribute("currentPage", page);
		model.addAttribute("sortBy", sortBy);
		model.addAttribute("sortType", sortType);
		model.addAttribute("numbercustomer", pageRs.getTotalElements());
		model.addAttribute("totalPage",pageRs.getTotalPages());
		return "/customer/customers";
	}

	@GetMapping("/customer/{id}")
	public String setEnable(@PathVariable("id") long id,@Param("enable") boolean enable,RedirectAttributes model) {
		customerService.changeEnable(id);
		String tmp = enable == true ? "Disabled" : "Enabled" ;
		model.addFlashAttribute("message", "Tài khoản có Id là "+id+" đã được "+tmp);
		return "redirect:/customer";
	}
	@PostMapping("/customer/edit")
	public String saveCustomer(Customer customer,RedirectAttributes model) {
		customerService.saveCustomer(customer);
		model.addFlashAttribute("message","Tài khoản khách hàng có Id là "+customer.getId()+" đã được cập nhập" );
		return "redirect:/customer";
	}
	
	
	@GetMapping("/customer/detail/{id}")
	public String getDetailCustomer(@PathVariable("id") Long id,Model model) {
	      Customer customer= customerService.getById(id);
	      model.addAttribute("customer", customer);
		return "/customer/customer_detail";
	}
}
