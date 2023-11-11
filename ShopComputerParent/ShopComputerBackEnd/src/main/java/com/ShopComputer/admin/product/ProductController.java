package com.ShopComputer.admin.product;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.ShopComputer.EntityCommon.Product;
import com.ShopComputer.EntityCommon.ProductDetail;
import com.ShopComputer.EntityCommon.ProductImage;
import com.ShopComputer.admin.FileUploadUtil;
import com.ShopComputer.admin.brand.BrandService;
import com.ShopComputer.admin.productDetail.ProductDetailService;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductDetailService productDetailService;

	@Autowired
	private BrandService brandService;

	@GetMapping("/products")
	public String getProducts(Model model) {
		return getByPage(1, "id", "asc", null, model);
	}

	@GetMapping("/products/page/{currentPage}")
	public String getByPage(@PathVariable("currentPage") int currentPage, @Param("sortBy") String sortBy,
			@Param("sortType") String sortType, @Param("keyWord") String keyWord, Model model) {

		Page<Product> pageRs = productService.getByPage(currentPage, sortBy, sortType, keyWord);
		String sortRever = sortType.equals("asc") ? "desc" : "asc";
		model.addAttribute("listProduct", pageRs.getContent());
		model.addAttribute("numberProduct", pageRs.getTotalElements());
		model.addAttribute("sortType", sortType);
		model.addAttribute("sortBy", sortBy);
		model.addAttribute("sortRever", sortRever);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPage", pageRs.getTotalPages());
		return "products/products";
	}

	@GetMapping("products/new")
	public String getFormAddProduct(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		model.addAttribute("listBrand", brandService.findAll());
		return "products/products_form";
	}

	@PostMapping("products/new")
	public String saveProduct(Product product,@RequestParam("imageM") MultipartFile mainImage,@RequestParam("img") MultipartFile [] imageExtra,
			RedirectAttributes model,@RequestParam("nameDetail")String nameDetails [],@RequestParam("valueDetail")String valueDetails [],@RequestParam("detailIds")Long [] detailIds) throws IOException {
		
		if(product.getId()== null) {
			product.setCreateTime(new Date());
			String nameMainImage= StringUtils.cleanPath(mainImage.getOriginalFilename());
			product.setMainImage(nameMainImage);
			
			for(MultipartFile multipartFile : imageExtra) {
				String nameMultipartFile = StringUtils.cleanPath(multipartFile.getOriginalFilename());
				if(!multipartFile.isEmpty()) {
					product.addProductImageExtra(nameMultipartFile);
				}
		    }
			Product productSave=productService.addProduct(product);
			String uploadDir="../product-photos/"+productSave.getId();
			FileUploadUtil.cleanDir(uploadDir);
			FileUploadUtil.saveFile(uploadDir, productSave.getMainImage(), mainImage);
			
			for(MultipartFile multipartFile : imageExtra) {
				String nameMultipartFile = StringUtils.cleanPath(multipartFile.getOriginalFilename());
				if(!multipartFile.isEmpty()) {
					String uploadDirExtra="../product-photos/"+productSave.getId()+"/extras";
					FileUploadUtil.saveFile(uploadDirExtra, nameMultipartFile, multipartFile);
				}
		    }
			saveDetail(nameDetails,valueDetails,product);
		}
		else {
			Product productTmp= productService.findById(product.getId());
			product.setUpdateTime(new Date());
			if(!mainImage.isEmpty()) {
				String nameMainImage= StringUtils.cleanPath(mainImage.getOriginalFilename());
				product.setMainImage(nameMainImage);
				String uploadDir="../product-photos/"+productTmp.getId();
				FileUploadUtil.cleanDir(uploadDir);
				FileUploadUtil.saveFile(uploadDir, productTmp.getMainImage(), mainImage);
				saveMainImage(mainImage,productTmp);
			}else {
				product.setMainImage(productTmp.getMainImage());
			}
			for(ProductImage s: productTmp.getProductImages()) {
				product.addProductImageExtra(s.getName());
			}
			product.setProductDetails(productTmp.getProductDetails());
			productService.addProduct(product);
//			chua code updateDetail và updateImg;
		}
		
		
		model.addFlashAttribute("message", "Đã lưu sản phẩm mới có tên là "+product.getName()+ " thành công !");
		return "redirect:/products";
	}

	@GetMapping("products/delete/{id}")
	public String deteleProduct(@PathVariable("id") Long id, RedirectAttributes model) {
		Product product = productService.findById(id);
		String name = product.getName();
		productService.deleteProduct(id);
		model.addFlashAttribute("message", "Đã xóa sản phẩm có tên là " + name + " thành công !");
		return "redirect:/products";
	}

	public void saveDetail(String detailName[], String detailValue[], Product product) {
		Set<ProductDetail> setRs = new HashSet<>();
		for (int i = 0; i < detailName.length; i++) {
			if (!detailName[i].equals("")) {
				productDetailService.save(new ProductDetail(detailName[i], detailValue[i], product));
			}
		}
	}
	
	@GetMapping("products/detail/{id}")
	public String getProduct(Model model,@PathVariable("id") Long id) {
		model.addAttribute("product", productService.findById(id));
		model.addAttribute("listBrand", brandService.findAll());
		return "products/products_form";
	}
	
//	public void saveDetails(String[] nameDetail,String[] valueDetail,Long[] detailIds,Product product) {
//		List<ProductDetail> listOldProductDetail= product.getProductDetails();
//		for(ProductDetail p: listOldProductDetail) {
//			boolean check=true;
//			for(Long l : detailIds) {
//				if(l == p.getId()) {
//					check=false;
//					break;
//				}
//			}
//			if(check==true) {
//				productDetailService.delete(p);
//			}
//		}
//	}
	
	public void saveMainImage(MultipartFile multipartFile,Product product) throws IOException {
		if(!multipartFile.isEmpty()) {
			String fileName= StringUtils.cleanPath(multipartFile.getOriginalFilename());
			product.setMainImage(fileName);
			String uploadDir="../product-photos/"+product.getId();
			FileUploadUtil.cleanDir(uploadDir);
			FileUploadUtil.saveFile(uploadDir, product.getMainImage(), multipartFile);
			
		}
	}
}
