package com.ShopComputer.site.cartItem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ShopComputer.EntityCommon.Bill;
import com.ShopComputer.EntityCommon.BillStatus;
import com.ShopComputer.EntityCommon.CartItem;
import com.ShopComputer.EntityCommon.Customer;
import com.ShopComputer.site.customer.CustomerService;

@Controller
public class BillController {
	@Autowired
	private BillService billService;
	
	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/bill/{id}")
	public String saveBill(@PathVariable("id") Long id,Bill bill,Model model) {
		List<CartItem> listCartItem= cartItemService.getAllProductInCart(id);
		bill.setStatus(BillStatus.WaitForConfirmation);
		billService.saveBill(listCartItem, bill);
		model.addAttribute("message", "Bạn đã đặt hàng thành công chúng tôi sẽ sớm liên lạc với bạn !");
		return "cart";		
	}
	
	@PostMapping("/confirmBuy/{id}")
	public String getFormConfirm(Model model,@PathVariable("id")Long id) {
		List<CartItem> cartItems= cartItemService.getAllProductInCart(id);
		Customer customer= customerService.findById(id);
		double tienHang=0;
		double tienPhaiTra=0;
		for(CartItem c: cartItems) {
			tienHang= tienHang+c.getProduct().getPrice();
			tienPhaiTra= tienPhaiTra+c.getProduct().getPriceSale();
		}
		double tienDuocGiam=tienHang - tienPhaiTra;
		Bill b = new Bill();
		b.setAddress(customer.getAddress1());
		model.addAttribute("bill", b);
		model.addAttribute("tienHang", tienHang);
		model.addAttribute("tienPhaiTra", tienPhaiTra);
		model.addAttribute("tienDuocGiam", tienDuocGiam);		
		model.addAttribute("phoneNumber", customer.getPhoneNumber());
		model.addAttribute("address", customer.getAddress1());
		return "confirmBuy";
	}

}
