package com.ShopComputer.admin.category;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ShopComputer.EntityCommon.Brand;
import com.ShopComputer.EntityCommon.Category;
import com.ShopComputer.admin.FileUploadUtil;

@Controller
public class CategoryController {
	
	@Autowired 
	private CategoryService categoryService;
	
	@GetMapping("/categories")
	public String getCategories(Model model) {
		return getCategoriesByPage(1, "id", "asc", model);
	}
	
	@GetMapping("/categories/page/{currentPage}")
	public String getCategoriesByPage(@PathVariable("currentPage") int currentPage,@Param("sortBy")String sortBy,@Param("sortType") String sortType,Model model) {
		Page<Category> pageRs= categoryService.getByPage(currentPage, sortBy, sortType, null);
		String sortRever=sortType.equals("asc")?"desc":"asc";
		model.addAttribute("listCategories", pageRs.getContent());
		model.addAttribute("numberCategory", pageRs.getTotalElements());
		model.addAttribute("sortType",sortType);
		model.addAttribute("sortBy", sortBy);
		model.addAttribute("sortRever", sortRever);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPage", pageRs.getTotalPages());
		return "categories/categories";
	}
	
	@GetMapping("categories/new")
	public String getFormAddCategory(Model model) {
		Category category = new Category();
		model.addAttribute("category", category);
		model.addAttribute("listCategories", categoryService.getListCategoriesForForm());
		return "categories/category_form";
	}
	
	@PostMapping("/categories/new")
	public String saveCategory(Category category,RedirectAttributes redirectAttributes,@RequestParam("photo") MultipartFile multipartFile) throws IOException {
		if(!multipartFile.isEmpty()) {
			String fileName= StringUtils.cleanPath(multipartFile.getOriginalFilename());
			category.setImage(fileName);
			Category categorySave= categoryService.saveCategory(category);
			 String uploadDir="../category-photos/"+categorySave.getId();
	         FileUploadUtil.cleanDir(uploadDir);
	         FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		}else {
			
				Category categoryTmp= categoryService.getById(category.getId());
				category.setImage(categoryTmp.getImage());
				Category categorySave= categoryService.saveCategory(category);
		
		}
		
		if(category.getId() == null) {
			redirectAttributes.addFlashAttribute("message","Danh mục có tên là "+category.getName()+" đã được lưu lại !");
		}else {
			redirectAttributes.addFlashAttribute("message","Danh mục có Id là "+category.getId()+" đã được cập nhập !");
		}
		return "redirect:/categories";
		
	}
	
	@GetMapping("categories/delete/{id}")
	public String deleteBrand(@PathVariable("id") Integer id,RedirectAttributes redirectAttributes) {
		Category category = categoryService.getById(id);
		String name= category.getName();
		boolean deleteCategory = categoryService.deteleCategory(id);
		if(deleteCategory == true) {
			redirectAttributes.addFlashAttribute("message","Xóa danh mục có tên là "+name+" thành công !");
		}else {
			redirectAttributes.addFlashAttribute("error","Không thể xóa danh mục có tên là "+name+" vì nó còn có các danh mục con khác !");
		}
		return "redirect:/categories";
	}
	
	@GetMapping("/categories/detail/{id}")
	public String getDetailBrand(@PathVariable("id") Integer id,Model model) {
		Category category = categoryService.getById(id);
		model.addAttribute("category", category);
		model.addAttribute("listCategories", categoryService.getListCategoriesForForm());
		return "categories/category_form";
	}

}
