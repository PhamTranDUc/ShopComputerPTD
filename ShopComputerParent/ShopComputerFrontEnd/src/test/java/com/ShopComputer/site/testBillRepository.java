package com.ShopComputer.site;

import com.ShopComputer.EntityCommon.Bill;
import com.ShopComputer.EntityCommon.CartItem;
import com.ShopComputer.site.cartItem.BillRepository;
import com.ShopComputer.site.cartItem.CartItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class testBillRepository {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Test
    public void testSaveBill(){
        List<CartItem> listCartItem= new ArrayList<>();
        CartItem c1= entityManager.find(CartItem.class,3L);
        CartItem c2= entityManager.find(CartItem.class,4L);
        CartItem c3= entityManager.find(CartItem.class,5L);
        CartItem c4= entityManager.find(CartItem.class,6L);
        listCartItem.addAll(List.of(c1,c2,c3,c4));
        Bill b= new Bill(null,"","KTX-B5 Học viện Bưu chính ");
        for(CartItem c : listCartItem){
            c.setBill(b);
            cartItemRepository.save(c);
        }
        billRepository.save(b);
    }
}
