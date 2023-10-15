package com.ShopComputer.admin;

import com.ShopComputer.EntityCommon.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class testUserRepository {

    @Autowired
    private com.ShopComputer.admin.user.UserRepository userRepository;

    @Test
    public void findByEmail(){
        User user= userRepository.findByEmail("phamtranducc@gmail.com");
        System.out.println(user.getFirstName());
    }
}
