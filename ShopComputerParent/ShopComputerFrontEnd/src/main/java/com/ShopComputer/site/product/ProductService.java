package com.ShopComputer.site.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ShopComputer.EntityCommon.Product;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public Page<Product> getByCategoryByPage(Integer currentPage, String sortBy, String sortType,Integer categoryId){
		Sort sort=Sort.by(sortBy);
		sort = sortType.equals("asc")?sort.ascending():sort.descending();
		Pageable pageable =PageRequest.of(currentPage-1, 10, sort);
		Page<Product> pageRs = productRepository.findAllByCategory(categoryId,pageable); 
		return pageRs;
	}
	
	
	public Page<Product> getByBrandByPage(Integer currentPage, String sortBy, String sortType,Integer brandId){
		Sort sort=Sort.by(sortBy);
		sort = sortType.equals("asc")?sort.ascending():sort.descending();
		Pageable pageable =PageRequest.of(currentPage-1, 10, sort);
		Page<Product> pageRs = productRepository.findAllByBrand(brandId,pageable); 
		return pageRs;
	}
	
	public Product findById(Long id) {
		return productRepository.findById(id).get();
	}
	
	

}
