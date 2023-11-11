package com.ShopComputer.site.product;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ShopComputer.EntityCommon.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> , PagingAndSortingRepository<Product, Long>{
	
	@Query("SELECT p FROM Product p WHERE p.enable = true")
	public List<Product> findAllEnable();
	
	@Query("SELECT p FROM Product p JOIN p.listCategory c WHERE c.id =:id AND p.enable = true AND c.enable = true")
	public Page<Product> findAllByCategory(@Param("id") Integer id, Pageable pageable);
	
	@Query("SELECT p FROM Product p JOIN  p.brand b WHERE b.id =:id AND p.enable = true")
	public Page<Product> findAllByBrand(@Param("id") Integer id,Pageable pageable);
	

	
	

}
