package com.ShopComputer.site.cartItem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ShopComputer.EntityCommon.Bill;
import com.ShopComputer.EntityCommon.CartItem;

@Service
public class BillService {
	@Autowired
	private BillRepository billRepository;
	
	@Autowired
	private CartItemRepository cartItemRepository;
	
	public void saveBill(List<CartItem> cartItems,Bill b) {
		billRepository.save(b);
		for(CartItem c: cartItems) {
			c.setBill(b);
			cartItemRepository.save(c);
		}
	}
	
	

}
