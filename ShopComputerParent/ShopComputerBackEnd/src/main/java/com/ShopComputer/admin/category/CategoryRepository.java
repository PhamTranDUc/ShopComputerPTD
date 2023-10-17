package com.ShopComputer.admin.category;

import com.ShopComputer.EntityCommon.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer>, PagingAndSortingRepository<Category,Integer> {

    @Query("SELECT c FROM Category c WHERE c.categoryParent = :category")
    public List<Category> getChildrenCategory(Category category);
}
