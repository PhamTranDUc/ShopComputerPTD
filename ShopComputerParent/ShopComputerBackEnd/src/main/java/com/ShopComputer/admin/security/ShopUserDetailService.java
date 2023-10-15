package com.ShopComputer.admin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ShopComputer.EntityCommon.User;
import com.ShopComputer.admin.user.UserRepository;

public class ShopUserDetailService implements UserDetailsService{
	@Autowired
	private UserRepository userRepository;
	



	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user= userRepository.findByEmail(email);
		if(user != null) {
			return new UserDetail(user);
		}
		throw new UsernameNotFoundException("Could not find user by email " + email);
	}

}
