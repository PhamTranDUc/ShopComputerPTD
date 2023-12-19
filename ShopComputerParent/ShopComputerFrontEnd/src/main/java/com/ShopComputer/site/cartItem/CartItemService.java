package com.ShopComputer.site.cartItem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ShopComputer.EntityCommon.CartItem;
import com.ShopComputer.EntityCommon.Customer;

@Service
public class CartItemService {
	
	@Autowired
	private CartItemRepository cartItemRepository;
	
//	public List<CartItem> getAllProductInCart(Long idCustomer){
//		List<CartItem> listRs= cartItemRepository.findAllNotPay(idCustomer);
//		return listRs;	
//	}
	public List<CartItem> getAllProductInCart(Customer customer){
		List<CartItem> listRs= cartItemRepository.findAllNotPay(customer);
		return listRs;	
	}
	
	public void saveCartItem(CartItem c) {
		cartItemRepository.save(c);
	}
	
	public void deteleCartItem(Long id) {
		cartItemRepository.deleteById(id);
	}

}
