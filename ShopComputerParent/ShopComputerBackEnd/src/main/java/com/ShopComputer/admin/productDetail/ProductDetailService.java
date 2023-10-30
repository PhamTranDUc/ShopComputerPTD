package com.ShopComputer.admin.productDetail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ShopComputer.EntityCommon.ProductDetail;

@Service
public class ProductDetailService {
	
	@Autowired
	private ProductDetailRepository productDetailRepository;
	
	public void save(ProductDetail productDetail) {
		productDetailRepository.save(productDetail);
	}
	
	public void delete(ProductDetail productDetail) {
		productDetailRepository.delete(productDetail);
	}

}
