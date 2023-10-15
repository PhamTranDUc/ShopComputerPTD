package com.ShopComputer.admin.brand;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ShopComputer.EntityCommon.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer>, PagingAndSortingRepository<Brand, Integer>{

}
