package com.ShopComputer.site.cartItem;

import java.security.Principal;
import java.util.List;

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
import com.ShopComputer.site.customer.CustomerRepository;
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
	
	@GetMapping("/cart/{id}")
	public String getCart(Model model,@PathVariable("id")Long id) {
		List<CartItem> listCartItems= cartItemService.getAllProductInCart(id);
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
	
	@GetMapping("/cart/addToCart/{idCustomer}/{idProduct}")
	public String addToCart(@PathVariable("idCustomer")Long idCustomer,@PathVariable("idProduct") Long idProduct,Model model) {
		Customer c= customerRepository.findById(idCustomer).get();
		Product p= productRepository.findById(idProduct).get();
		CartItem cartItem= new CartItem(p, c, 1);
		cartItemService.saveCartItem(cartItem);
		model.addAttribute("message", "Thêm sản phẩm vào giỏ hàng thành công!");
		model.addAttribute("product", p);
		return "product_detail";
	}
	
	@GetMapping("/cart/detele/{id}")
	public String deteleCartItem(@PathVariable("id") Long id, Model model) {
		long userId=0;
		cartItemService.deteleCartItem(id);
		model.addAttribute("message", "Xóa sản phẩm khỏi giỏ hàng thành công");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    // Kiểm tra xem người dùng đã đăng nhập chưa
	    if (authentication != null && authentication.isAuthenticated()) {
	        // Lấy thông tin về tài khoản đang đăng nhập, ví dụ: id
	    	  CustomerUserDetail userDetails = (CustomerUserDetail) authentication.getPrincipal();
	         userId = userDetails.getId();}
		return getCart(model, userId);
		
	}

}
