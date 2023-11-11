package com.ShopComputer.admin.product;

import javax.persistence.Transient;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ShopComputer.EntityCommon.Product;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long>, CrudRepository<Product, Long>{

	@Modifying
	@Transient
    @Query("UPDATE Product p SET p.quantity = p.quantity + :quantity WHERE p.id =:id")
    public void addProductToWareHouse(@Param("quantity")int quantity ,@Param("id")Long id);
	
	@Modifying
	@Transient
	@Query("UPDATE Product p SET p.quantity = p.quantity - :quantity WHERE p.id =:id")
	public void removeProductFromWareHouse(@Param("quantity") int quantity,@Param("id") Long id);
	
	
	@Modifying
	@Transient
	@Query("UPDATE Product p SET p.quantity = :quantity WHERE p.id =:id")
	public void setQuantityProduct(@Param("quantity") int quantity,@Param("id") Long id);
	
	

}
