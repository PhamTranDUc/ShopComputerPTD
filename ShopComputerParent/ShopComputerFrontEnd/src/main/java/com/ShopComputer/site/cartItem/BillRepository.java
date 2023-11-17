package com.ShopComputer.site.cartItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ShopComputer.EntityCommon.Bill;

@Repository
public interface BillRepository extends JpaRepository<Bill,Long>{
	

}
