package com.ShopComputer.admin;

import com.ShopComputer.EntityCommon.Category;
import com.ShopComputer.admin.category.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class testCategoryRepository {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void saveCategory(){
        Category category1 = new Category("Computer","COMPUTER",null,true,null,null);
        categoryRepository.save(category1);
    }

}
