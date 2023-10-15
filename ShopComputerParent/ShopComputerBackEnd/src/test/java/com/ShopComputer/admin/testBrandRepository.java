package com.ShopComputer.admin;

import com.ShopComputer.EntityCommon.Brand;
import com.ShopComputer.admin.brand.BrandRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class testBrandRepository {

    @Autowired
    private BrandRepository brandRepository;

    @Test
    public void testSave(){
        Brand brand1 = new Brand("Samsung");
        Brand brand2 = new Brand("Apple");
        Brand brand3 = new Brand("Dell");
        Brand brand4 = new Brand("Lenovo");
        Brand brand5 = new Brand("Acer");
        Brand brand6 = new Brand("Asus");
        Brand brand7 = new Brand("Logitech");
        Brand brand8 = new Brand("Beats");
        Brand brand9 = new Brand("Akko");
        Brand brand10 = new Brand("Fuhlen");

        brandRepository.saveAll(List.of(brand1,brand2,brand3,brand4,brand5,brand6,brand7,brand8,brand9,brand10));

    }

}
