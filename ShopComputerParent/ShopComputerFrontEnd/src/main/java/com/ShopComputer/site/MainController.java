package com.ShopComputer.site;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ShopComputer.site.brand.BrandRepository;
import com.ShopComputer.site.category.CategoryRepository;

@Controller
public class MainController {
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private BrandRepository brandRepository;
	
	
	
	@GetMapping("")
	public String viewHomePage(Model model) {
		model.addAttribute("listCategory",categoryRepository.findAllEnable());
		model.addAttribute("listBrand", brandRepository.findAll());
		return "index";
	}
	
	@GetMapping("/testLogin")
	public String viewLoginTest() {
		return "login";
	}

}
