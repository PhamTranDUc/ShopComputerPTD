package com.ShopComputer.site.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.ShopComputer.EntityCommon.AuthenticationType;
import com.ShopComputer.EntityCommon.Customer;
import com.ShopComputer.site.customer.CustomerService;

@Component
public class DatabaseLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{

	@Autowired
	@Lazy
	private CustomerService customerService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		
		CustomerUserDetail userDetail= (CustomerUserDetail) authentication.getPrincipal();
		Customer customer = userDetail.getCustomer();
		customerService.updateAuthentication(customer,AuthenticationType.DATABASE);		
		super.onAuthenticationSuccess(request, response, authentication);
	}
	
	
}
