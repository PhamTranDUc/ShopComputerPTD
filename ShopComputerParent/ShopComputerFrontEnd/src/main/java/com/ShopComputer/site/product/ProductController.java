package com.ShopComputer.site.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ShopComputer.EntityCommon.Product;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	
	@GetMapping("/products/category/{id}/page/{currentPage}")
	public String getAllByCategoryByPage(Model model, @PathVariable("id") Integer id,@Param("sortBy") String sortBy,
			@Param("sortType") String sortType, @PathVariable("currentPage") Integer currentPage) {
		Page<Product> pageRs= productService.getByCategoryByPage(currentPage, sortBy, sortType, id);
		model.addAttribute("listProduct",pageRs.getContent());
		model.addAttribute("sortBy",sortBy);
		model.addAttribute("sortType",sortType);
		model.addAttribute("currentPage",currentPage);
		model.addAttribute("numberProduct",pageRs.getTotalElements());
		model.addAttribute("totalPage", pageRs.getTotalPages());
		model.addAttribute("categoryId", id);
		return "products_for_category";
	}
	
	@GetMapping("/products/category/{id}")
	public String getAllByCategory(Model model,@PathVariable("id") Integer id) {
		Page<Product> pageRs= productService.getByCategoryByPage(1,"id", "desc", id);
		return getAllByCategoryByPage(model, id, "id", "desc", 1);
	}
	
	@GetMapping("/products/brand/{id}/page/{currentPage}")
	public String getAllByBrandByPage(Model model, @PathVariable("id") Integer id,@Param("sortBy") String sortBy,
			@Param("sortType") String sortType, @PathVariable("currentPage") Integer currentPage) {
		Page<Product> pageRs= productService.getByBrandByPage(currentPage, sortBy, sortType, id);
		model.addAttribute("listProduct",pageRs.getContent());
		model.addAttribute("sortBy",sortBy);
		model.addAttribute("sortType",sortType);
		model.addAttribute("currentPage",currentPage);
		model.addAttribute("numberProduct",pageRs.getTotalElements());
		model.addAttribute("totalPage", pageRs.getTotalPages());
		model.addAttribute("brandId", id);
		return "products_for_brand";
	}
	
	
	@GetMapping("/products/brand/{id}")
	public String getAllByBrand(Model model,@PathVariable("id") Integer id) {
		Page<Product> pageRs= productService.getByBrandByPage(1,"id", "desc", id);
		return getAllByBrandByPage(model, id, "id", "desc", 1);
	}
	
	@GetMapping("products/detail/{id}")
	public String getDetailProduct(Model model,@PathVariable("id")Long id) {
		Product product= productService.findById(id);
		model.addAttribute("title", product.getName());
		model.addAttribute("product", product);
		return "product_detail";
	}
	
	

}
