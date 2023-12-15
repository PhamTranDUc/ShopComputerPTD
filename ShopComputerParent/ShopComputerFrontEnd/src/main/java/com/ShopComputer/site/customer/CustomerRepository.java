package com.ShopComputer.site.customer;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ShopComputer.EntityCommon.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
	@Query("SELECT c FROM Customer c WHERE c.email =:email AND c.enable = true")
	public Optional<Customer> findByEmail(@Param("email")String email);
	
	
	@Query("UPDATE Customer c SET c.enable = true WHERE c.id =:id")
	@Modifying
	public void enableCustomer(Long id);

	
	@Query("UPDATE Customer c Set c.enable = true, verificationCode = null WHERE c.verificationCode =:code")
	@Modifying
	public void verifyCustomer(String code);
	
	public Optional<Customer> findByVerificationCode(String code);

}
