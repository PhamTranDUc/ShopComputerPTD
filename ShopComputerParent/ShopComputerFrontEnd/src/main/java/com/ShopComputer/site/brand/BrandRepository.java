package com.ShopComputer.site.brand;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ShopComputer.EntityCommon.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand,Integer>{
	


}
