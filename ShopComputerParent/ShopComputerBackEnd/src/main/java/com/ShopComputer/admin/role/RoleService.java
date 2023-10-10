package com.ShopComputer.admin.role;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ShopComputer.EntityCommon.Role;

@Service
public class RoleService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	public List<Role> getAll(){
	 
		return roleRepository.findAll();
	}
	
	

}