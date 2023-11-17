package com.ShopComputer.site.cartItem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ShopComputer.EntityCommon.CartItem;

@Service
public class CartItemService {
	
	@Autowired
	private CartItemRepository cartItemRepository;
	
	public List<CartItem> getAllProductInCart(Long idCustomer){
		List<CartItem> listRs= cartItemRepository.findAllNotPay(idCustomer);
		return listRs;	
	}
	
	
	public void saveCartItem(CartItem c) {
		cartItemRepository.save(c);
	}
	
	public void deteleCartItem(Long id) {
		cartItemRepository.deleteById(id);
	}

}
