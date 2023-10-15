package com.ShopComputer.admin.brand;

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
import com.ShopComputer.EntityCommon.User;
import com.ShopComputer.admin.FileUploadUtil;

@Controller
public class BrandController {
	@Autowired
	private BrandService brandService;
	
	@GetMapping("/brands")
	public String getBrands(Model model) {
		return getBrandByPage(1, "id", "asc", model);
	}
	
	@GetMapping("/brands/page/{currentPage}")
	public String getBrandByPage(@PathVariable("currentPage") int currentPage,@Param("sortBy")String sortBy,@Param("sortType") String sortType,Model model) {
		Page<Brand> pageRs= brandService.getByPage(currentPage, sortBy, sortType, null);
		String sortRever=sortType.equals("asc")?"desc":"asc";
		model.addAttribute("listBrand", pageRs.getContent());
		model.addAttribute("numberBrand", pageRs.getTotalElements());
		model.addAttribute("sortType",sortType);
		model.addAttribute("sortBy", sortBy);
		model.addAttribute("sortRever", sortRever);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPage", pageRs.getTotalPages());
		return "brands/brands";
	}
	
	@GetMapping("brands/new")
	public String getFormAddBrand(Model model) {
		Brand brand = new Brand();
		model.addAttribute("brand", brand);
		return "brands/brand_form";
	}
	
	@PostMapping("/brands/new")
	public String saveBrand(Brand brand,RedirectAttributes redirectAttributes,@RequestParam("photo") MultipartFile multipartFile) throws IOException {
		String fileName= StringUtils.cleanPath(multipartFile.getOriginalFilename());
		brand.setImage(fileName);
		Brand brandSave= brandService.save(brand);
		 String uploadDir="../brand-photos/"+brandSave.getId();
         FileUploadUtil.cleanDir(uploadDir);
         FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		if(brand.getId() == null) {
			redirectAttributes.addFlashAttribute("message","Thương hiệu có tên là "+brand.getName()+" đã được lưu lại !");
		}else {
			redirectAttributes.addFlashAttribute("message","Thương hiệu có Id là "+brand.getId()+" đã được cập nhập !");
		}
		
		return "redirect:/brands";
		
	}
	
	@GetMapping("brands/delete/{id}")
	public String deleteBrand(@PathVariable("id") Integer id,RedirectAttributes redirectAttributes) {
		Brand brand= brandService.getById(id);
		String name= brand.getName();
		brandService.deletel(id);
		redirectAttributes.addFlashAttribute("message","Xóa thương hiệu có tên là "+name+" thành công !");
		return "redirect:/brands";
	}
	
	@GetMapping("/brands/detail/{id}")
	public String getDetailBrand(@PathVariable("id") Integer id,Model model) {
		Brand brand = brandService.getById(id);
		model.addAttribute("brand", brand);
		return "brands/brand_form";
	}
}
