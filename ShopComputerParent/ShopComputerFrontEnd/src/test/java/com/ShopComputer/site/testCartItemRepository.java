package com.ShopComputer.site;

import com.ShopComputer.EntityCommon.CartItem;
import com.ShopComputer.EntityCommon.Customer;
import com.ShopComputer.EntityCommon.Product;
import com.ShopComputer.site.cartItem.CartItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.persistence.EntityManager;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class testCartItemRepository {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void testSave(){
        CartItem cartItem= new CartItem(entityManager.find(Product.class,25L),entityManager.find(Customer.class,1L),1);
        cartItemRepository.save(cartItem);

    }

    @Test
    public void testGetByCustomer(){
        List<CartItem> listRs= cartItemRepository.findByCustomer(1L);
        for(CartItem c: listRs){
            System.out.println(c);
            System.out.println("so phan tu la : ");
            System.out.println(listRs.size());
        }

    }

    @Test
    public void findAllNotPay(){
        List<CartItem> listRs= cartItemRepository.findAllNotPay(1L);
        for(CartItem c: listRs){
            System.out.println(c);
            System.out.println("so phan tu la : ");
            System.out.println(listRs.size());
        }

    }

}
