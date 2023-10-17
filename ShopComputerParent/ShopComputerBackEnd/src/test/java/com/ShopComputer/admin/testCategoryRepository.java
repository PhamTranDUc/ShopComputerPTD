package com.ShopComputer.admin;

import com.ShopComputer.EntityCommon.Category;
import com.ShopComputer.admin.category.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)

public class testCategoryRepository {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void saveCategory(){
//        Category category1 = new Category("Computer","COMPUTER",null,true,null,null);
//        categoryRepository.save(category1);

        Category category2 = new Category("Linh kiện","LINH KIỆN",null,true,null,null);
        categoryRepository.save(category2);
    }

    @Test
    public void saveSubCategory(){
//        Category categoryParent= categoryRepository.findById(1).get();
//        Category category1= new Category("Laptop","LAPTOP",null,true,categoryParent,null);
//        Category category2= new Category("PC","PC",null,true,categoryParent,null);
//        categoryRepository.saveAll(List.of(category1,category2));



//        Category categoryParent= categoryRepository.findById(4).get();
//        Category category1= new Category("CPU","CPU",null,true,categoryParent,null);
//        Category category2= new Category("VGA","VGA",null,true,categoryParent,null);
//        Category category3= new Category("SSD","SSD",null,true,categoryParent,null);
//        Category category4= new Category("HDD","HDD",null,true,categoryParent,null);
//        Category category5= new Category("Case máy tính","CASE",null,true,categoryParent,null);
//        Category category6= new Category("Màn hình","MONITOR",null,true,categoryParent,null);
//
//        categoryRepository.saveAll(List.of(category1,category2,category3,category4,category5,category6));

//        Category categoryParent= categoryRepository.findById(2).get();
//        Category category1= new Category("Gaming","GAME",null,true,categoryParent,null);
//        Category category2= new Category("Macbook","MAC",null,true,categoryParent,null);
//        Category category3= new Category("Học tập - Văn phòng","STU",null,true,categoryParent,null);
//        Category category4= new Category("Đồ họa - Kỹ thuật","GRAPHICS",null,true,categoryParent,null);
//        Category category5= new Category("Mỏng nhẹ","SLIM",null,true,categoryParent,null);
//        Category category6= new Category("Cao cấp","LUX",null,true,categoryParent,null);
//        categoryRepository.saveAll(List.of(category1,category2,category2,category3,category4,category5,category6));


        Category categoryParent= categoryRepository.findById(6).get();
        Category category1= new Category("VGA1","VGA1",null,true,categoryParent,null);
        Category category2= new Category("VGA2","VGA2",null,true,categoryParent,null);

        Category category3= new Category("VGA3","VGA3",null,true,category1,null);
        Category category4= new Category("VGA4","VGA4",null,true,category3,null);
        categoryRepository.saveAll(List.of(category1,category2,category3,category4));

    }

    @Test
    public void printCategoryChildren(){
        Iterable<Category> listCategory= categoryRepository.findById(2).get().getChildren();
        for(Category category : listCategory){
            System.out.println(category.getName());
        }
    }

    @Test
    public void printAllCategory(){
        Iterable<Category> listCategory = categoryRepository.findAll();
        List<Category> listCategoryRoot = new ArrayList<>();
        for(Category c: listCategory){
            if(c.getCategoryParent() == null){
                listCategoryRoot.add(c);
            }
        }
        for(Category category : listCategoryRoot){
            System.out.print("--");
            System.out.println(category.getName());
            printSubCategory(1,category);
        }
    }

    public void printSubCategory(int subLevel,Category category){

        subLevel+=1;
        Iterable<Category> subCategory = category.getChildren();

        for(Category c: subCategory){
            for(int i=0;i<subLevel;i++){
                System.out.print("--");
            }
            System.out.println(c.getName());
            printSubCategory(subLevel,c);
        }
    }

    @Test
    public void testGetChildren(){
        Category category= categoryRepository.findById(16).get();
        List<Category> listChildren= categoryRepository.getChildrenCategory(category);
//        for(Category c : listChildren){
//            System.out.println(c.getName());
//        }
        Assertions.assertEquals(listChildren.size(),0);
    }

}
