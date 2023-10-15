package com.ShopComputer.admin.brand;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ShopComputer.EntityCommon.Brand;

@Service
public class BrandService {
	
	@Autowired
	private BrandRepository brandRepository;
	
	public Brand save(Brand brand) {
		
		return brandRepository.save(brand);
	}
	
	public void deletel(Integer id) {
		brandRepository.deleteById(id);
	}
	public Page<Brand> getByPage(int currentPage,String sortBy, String sortType,String keyWord){
		Sort sort= Sort.by( sortBy);
		sort = sortType.equals("asc")?sort.ascending():sort.descending();
		
		Pageable pageable = PageRequest.of(currentPage-1, 6, sort);
		
		Page<Brand> pageRs= brandRepository.findAll(pageable);
		return pageRs;
		
	}
	
	public Brand getById(Integer id) {
		Brand brand = brandRepository.findById(id).get();
		if(brand == null) {
			return null;
		}
		return brand;
	}

}
