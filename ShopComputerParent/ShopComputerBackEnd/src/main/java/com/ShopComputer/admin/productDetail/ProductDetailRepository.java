package com.ShopComputer.admin.productDetail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ShopComputer.EntityCommon.ProductDetail;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail,Long>{

}
