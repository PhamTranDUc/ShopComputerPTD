package com.ShopComputer.admin.customer;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ShopComputer.EntityCommon.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> , PagingAndSortingRepository<Customer, Long>{

	@Query("UPDATE Customer c Set c.enable = true WHERE c.id =:id")
	@Modifying
	public void setEnable(Long id);
	
	@Query("UPDATE Customer c Set c.enable = false WHERE c.id =:id")
	@Modifying
	public void setDisable(Long id);
}
