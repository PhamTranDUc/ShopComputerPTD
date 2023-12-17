package com.ShopComputer.site.security.oauth;

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
public class OAuth2LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{

	@Autowired
	@Lazy
	private CustomerService customerService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {

		CustomerOauth2User oauth2User = (CustomerOauth2User) authentication.getPrincipal();
		String email= oauth2User.getEmail();
		String name= oauth2User.getName();
		Customer customer= customerService.getByEmail(email);
		System.out.println("email vs name :" + email+ " "+ name);
		if(customer == null) {
			customerService.addNewCustomer(email,name);
		}else {
			customerService.updateAuthentication(customer,AuthenticationType.GOOGLE);
		}
		
		super.onAuthenticationSuccess(request, response, authentication);
	}

	
}
