package com.ShopComputer.admin.category;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ShopComputer.EntityCommon.Category;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<Category> findAll(){
		return categoryRepository.findAll();
	}
	
	public boolean deteleCategory(Integer id) {
		Category categoryTmp= categoryRepository.getById(id);
        List<Category> listCategory = categoryRepository.getChildrenCategory(categoryTmp);
        if(listCategory.size()==0) {
        	categoryRepository.deleteById(id);
        	return true;
        }
        return false;
	}
	public Category saveCategory(Category category) {
		return categoryRepository.save(category);
	}
	public Category getById(Integer id) {
		return categoryRepository.getById(id);
	}
	
	public Page<Category> getByPage(int CurrentPage,String sortBy, String sortType,String keyWord){
		Sort sort = Sort.by(sortBy);
		sort = sortType.equals("asc")?sort.ascending():sort.descending();
		Pageable pageable= PageRequest.of(CurrentPage-1,6, sort);
		Page<Category> pageRs= categoryRepository.findAll(pageable);
		return pageRs;
	}
	
	public List<Category> getListCategoriesForForm(){
		List<Category> listCategories= getAllCategory();
		return listCategories;
		
	}

    public List<Category> getAllCategory(){
        Iterable<Category> listCategory = categoryRepository.findAll();
        List<Category> listCategoryRoot = new ArrayList<>();
        for(Category c: listCategory){
            if(c.getCategoryParent() == null){
                listCategoryRoot.add(c);
            }
        }
        List<Category> listCategoryRs= new ArrayList<>();
        for(Category category : listCategoryRoot){
        	String name = "--"+ category.getName();
            Category c = new Category(category.getId(),name,category.getAlias());
            listCategoryRs.add(c);
            addSubCategory(listCategoryRs,1,category);
        }
        return listCategoryRs;
    }
    
    public void addSubCategory(List<Category> listRs,int subLevel,Category category){

        subLevel+=1;
        Iterable<Category> subCategory = category.getChildren();

        for(Category c: subCategory){
        	String name ="";
            for(int i=0;i<subLevel;i++){
                name+="--";
            }
            Category tmp = new Category(c.getId(), name+c.getName(), c.getAlias());
            listRs.add(tmp);
            addSubCategory(listRs, subLevel, c);
        }
    }
	

}
