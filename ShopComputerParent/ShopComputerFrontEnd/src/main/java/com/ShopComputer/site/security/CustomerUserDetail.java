package com.ShopComputer.site.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ShopComputer.EntityCommon.Customer;
import com.ShopComputer.EntityCommon.User;

public class CustomerUserDetail implements UserDetails{
	private Customer customer;
	
	

	public CustomerUserDetail(Customer customer) {
		super();
		this.customer = customer;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.customer.getPassWord();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.customer.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return this.customer.enable;
	}
	
	public String getFullName() {
		return this.customer.firstName+" "+this.customer.getLastName();
	}
	
	public Long getId() {
		return this.customer.id;
	}
	
	public Customer getCustomer() {
		return this.customer;
	}


}
