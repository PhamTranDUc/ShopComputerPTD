package com.ShopComputer.site;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
	
	
	
	@GetMapping("/login")
	public String viewLoginPage(Model model) {
		Authentication authentication =SecurityContextHolder.getContext().getAuthentication();
		if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			return "login";
		}
		return getIndex(model);
	}
	
	@GetMapping("/")
	public String getIndex(Model model) {
		model.addAttribute("listBrand", brandRepository.findAll());
		model.addAttribute("listCategory", categoryRepository.findAll());
		return "index";
	}

}
