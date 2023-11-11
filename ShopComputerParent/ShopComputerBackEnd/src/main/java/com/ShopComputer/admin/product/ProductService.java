package com.ShopComputer.admin.product;

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
	
	public Page<Product> getByPage(int currentPage,String sortBy,String sortType,String keyWord){
		Sort sort= Sort.by(sortBy);
		sort = sortType.equals("asc")?sort.ascending():sort.descending();
		
		Pageable pageable = PageRequest.of(currentPage-1, 6, sort);
		return productRepository.findAll(pageable);
		
	}
	
	public Product addProduct(Product product) {
		return productRepository.save(product);
	}
	
	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}
	public Product findById(Long id) {
		return productRepository.findById(id).get();
	}

//	public Product importGoods(int quantity, Product product) {
//		product.setQuantity(quantity);
//		
//	}
}
