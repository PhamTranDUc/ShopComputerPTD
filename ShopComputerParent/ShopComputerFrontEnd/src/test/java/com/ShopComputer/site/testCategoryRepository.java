package com.ShopComputer.site;

import com.ShopComputer.EntityCommon.Category;
import com.ShopComputer.site.category.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class testCategoryRepository {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void testFindAllEnable(){
        List<Category> listRs=categoryRepository.findAllEnable();
        for(Category c: listRs){
            System.out.println(c.getName());
        }
    }
}
