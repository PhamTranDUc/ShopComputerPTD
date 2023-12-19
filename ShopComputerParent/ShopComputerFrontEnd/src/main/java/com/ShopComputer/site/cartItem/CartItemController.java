package com.ShopComputer.site.cartItem;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ShopComputer.EntityCommon.CartItem;
import com.ShopComputer.EntityCommon.Customer;
import com.ShopComputer.EntityCommon.Product;
import com.ShopComputer.site.Utility;
import com.ShopComputer.site.customer.CustomerRepository;
import com.ShopComputer.site.customer.CustomerService;
import com.ShopComputer.site.product.ProductRepository;
import com.ShopComputer.site.security.CustomerUserDetail;

@Controller
public class CartItemController {
	@Autowired
	private CartItemService cartItemService;
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/cart")
	public String getCart(Model model, HttpServletRequest request) {
		Customer customer = getAuthenticatedCustomer(request);
		List<CartItem> listCartItems= cartItemService.getAllProductInCart(customer);
		if(listCartItems.size() == 0) {
			model.addAttribute("listCartItem",null);
		}else {
			model.addAttribute("listCartItem",listCartItems);
		}
		
		Double tienHang= (double) 0;
		Double tienPhaiTra=(double) 0;
		for(CartItem c: listCartItems) {
			tienHang= tienHang+c.getProduct().getPrice();
			tienPhaiTra=tienPhaiTra+c.getProduct().getPriceSale();
		}
		Double tienDuocGiam=tienHang - tienPhaiTra;
		model.addAttribute("tienPhaiTra", tienPhaiTra);
		model.addAttribute("tienHang", tienHang);
		model.addAttribute("tienDuocGiam", tienDuocGiam);
		
		
		return "cart";
	}
	
	@GetMapping("/cart/addToCart/{idProduct}")
	public String addToCart(@PathVariable("idProduct") Long idProduct,Model model,  HttpServletRequest request) {
		Customer c= getAuthenticatedCustomer(request);
		Product p= productRepository.findById(idProduct).get();
		CartItem cartItem= new CartItem(p, c, 1);
		cartItemService.saveCartItem(cartItem);
		model.addAttribute("message", "Thêm sản phẩm vào giỏ hàng thành công!");
		model.addAttribute("product", p);
		return "product_detail";
	}
	
	@GetMapping("/cart/detele/{id}")
	public String deteleCartItem(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
		long userId=0;
//		Customer customer = getAuthenticatedCustomer(request);
		cartItemService.deteleCartItem(id);
		model.addAttribute("message", "Xóa sản phẩm khỏi giỏ hàng thành công");
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//	    // Kiểm tra xem người dùng đã đăng nhập chưa
//	    if (authentication != null && authentication.isAuthenticated()) {
//	        // Lấy thông tin về tài khoản đang đăng nhập, ví dụ: id
//	    	  CustomerUserDetail userDetails = (CustomerUserDetail) authentication.getPrincipal();
//	         userId = userDetails.getId();}
		return getCart(model, request);
		
	}
	
	private Customer getAuthenticatedCustomer(HttpServletRequest request) {
		String email = Utility.getEmailOfAuthenticatedCustomer(request);				
		return customerService.getCustomerByEmail(email);
	}	

}
