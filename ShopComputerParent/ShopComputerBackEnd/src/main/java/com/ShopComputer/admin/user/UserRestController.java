package com.ShopComputer.admin.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/checkEmailDuplicated")
	public String checkEmailDuplicated(@Param("id") Long id,@Param("email") String email) {
		return userService.checkEmailDuplicated(id, email)?"True":"Duplicated";
	}

}
