package com.ShopComputer.site;

import com.ShopComputer.EntityCommon.Product;
import com.ShopComputer.site.product.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class testProductRepository {

    @Autowired
    private ProductRepository productRepository;


    @Test
    public void findAllEnable(){
        List<Product> listRs= productRepository.findAllEnable();
        for(Product p: listRs){
            System.out.println(p.getName());
        }
    }

    @Test
    public void findAllByCategory(){
        Sort sort = Sort.by("id");
        sort.ascending();
        Pageable pageable = PageRequest.of(0,10,sort);
        List<Product> listRs= productRepository.findAllByCategory(2,pageable).getContent();
        int count=0;
        for(Product p: listRs){
            System.out.println(p.getName());
            count++;
        }
        System.out.println(count);
    }

    @Test
    public void findAllByBrand(){
        Sort sort = Sort.by("id");
        sort.ascending();
        Pageable pageable = PageRequest.of(0,10,sort);
        List<Product> listRs= productRepository.findAllByBrand(6,pageable).getContent();
        int count=0;
        for(Product p: listRs){
            System.out.println(p.getName());
            count++;
        }
        System.out.println(count);
    }
}
