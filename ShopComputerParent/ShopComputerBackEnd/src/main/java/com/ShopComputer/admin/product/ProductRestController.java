package com.ShopComputer.admin.product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ShopComputer.EntityCommon.Brand;
import com.ShopComputer.EntityCommon.Category;
import com.ShopComputer.admin.brand.BrandService;

@RestController
public class ProductRestController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private BrandService brandService;
	
	@GetMapping("/products/{id}/categories")
	public List<Category> getListCategoriesByBrand(@PathVariable("id") Integer id){
		Brand brand = brandService.getById(id);
		List<Category> listCategory= brand.getCategories();
		List<Category> listRs = new ArrayList<>();
		for(Category c: listCategory) {
			Category c1=  new Category(c.getId(), c.getName());
			listRs.add(c1 );
		}
		
		return listRs;
	}

}
