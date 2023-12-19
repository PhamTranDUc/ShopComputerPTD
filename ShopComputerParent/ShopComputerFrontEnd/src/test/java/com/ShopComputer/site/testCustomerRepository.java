package com.ShopComputer.site;

import com.ShopComputer.EntityCommon.AuthenticationType;
import com.ShopComputer.EntityCommon.CartItem;
import com.ShopComputer.EntityCommon.Customer;
import com.ShopComputer.site.cartItem.CartItemRepository;
import com.ShopComputer.site.customer.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class testCustomerRepository {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void saveCustomer(){
        Customer c1= new Customer("Trần Văn","Sỹ","syNguyenVan@gmail.com","0122293627",
                "Mỗ Lao-Hà Đông-Hà Nội","","123456",new Date(),true,"");
        Customer c2= new Customer("Nguyễn Quang","Trí","tringuyenquang@gmail.com","0862293123",
                "Tiên Kiên-Lâm Thao-Phú Thọ-Hà Nội","","123456",new Date(),true,"");
        Customer c3= new Customer("Đặng Quang","Linh","dangquanlinh@gmail.com","0862212345",
                "Xuân Lũng-Phú Thọ-Hà Nội","","123456",new Date(),true,"");
        Customer c4= new Customer("Nguyễn Thị","Linh","lingnuyenthi@gmail.com","0912293627",
                "Cầu Giấy-Hà Nội","","123456",new Date(),true,"");
        customerRepository.saveAll(List.of(c1,c2,c3,c4));
    }

    @Test
    public void findByEmail(){
        Customer c= customerRepository.findByEmail("phamtranducc@gmail.com").get();
        System.out.println(c.getFirstName()+" "+c.getLastName());
    }
    
    @Test
    public void enableCustomer() {
    	customerRepository.enableCustomer(14L);
    }
    
    @Test
    public void verifyCustomer() {
    	customerRepository.verifyCustomer("12345");
    }
    
    @Test
    public void findByVerificationCode() {
    	Optional<Customer> customer = customerRepository.findByVerificationCode("12345");
    	if(customer.isPresent()) {
    		System.out.println(customer.get().email);
    	}
    	
    }
    
    @Test
    public void updateAuthenticationType() {
    	customerRepository.updateAuthenticationType(2L, AuthenticationType.DATABASE);
    }
    

}
