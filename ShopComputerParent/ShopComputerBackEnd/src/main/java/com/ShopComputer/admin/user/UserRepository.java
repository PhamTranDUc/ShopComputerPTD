package com.ShopComputer.admin.user;

import com.ShopComputer.EntityCommon.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User,Long>, CrudRepository<User, Long> {
	
	@Modifying
	@Query("UPDATE User u SET u.enable = :enable WHERE u.id = :id ")
	public void setEnable(boolean enable,Long id);


	@Query("SELECT  u FROM User u WHERE u.email = :email")
	public User findByEmail(String email);
}
