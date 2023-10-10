package com.ShopComputer.admin.user;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ShopComputer.EntityCommon.User;



@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	
	public Page<User> getByPage(String sortBy,String sortType,int currentPage,String keyWord){
		Sort sort =  Sort.by(sortBy);
		sort=(sortType.equals("asc"))?sort.ascending():sort.descending();
		Pageable pageable=PageRequest.of(currentPage-1, 6, sort);
		Page<User> pageRs = userRepository.findAll(pageable);
	 return pageRs;
	}
	
	
	public void saveUser(User user) {
		userRepository.save(user);
	}
	
	public Long deleteUserById(Long id) {
		userRepository.deleteById(id);
		return id;
	}
	
	
    @Transactional
	public void setEnable(Long id,boolean enable) {
		userRepository.setEnable(enable, id);
	}
    
    public User findById(Long id) {
    	return userRepository.findById(id).get();
    }
}
