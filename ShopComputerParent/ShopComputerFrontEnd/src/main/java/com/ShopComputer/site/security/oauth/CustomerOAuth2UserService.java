package com.ShopComputer.site.security.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.ShopComputer.EntityCommon.AuthenticationType;
import com.ShopComputer.EntityCommon.Customer;
import com.ShopComputer.site.customer.CustomerRepository;
import com.ShopComputer.site.customer.CustomerService;

@Service
public class CustomerOAuth2UserService extends DefaultOAuth2UserService {

	@Autowired
	private CustomerRepository customerRepository;
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		String clientName= userRequest.getClientRegistration().getClientName();
		OAuth2User user = super.loadUser(userRequest);
		
		 
		return new CustomerOauth2User(user,clientName);
	}

	
}
