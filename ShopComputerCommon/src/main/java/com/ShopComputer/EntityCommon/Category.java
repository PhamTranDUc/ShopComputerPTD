package com.ShopComputer.EntityCommon;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

@Entity
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique = true)
	private String name;
	
	private String alias;
	
	private String image;
	
	private boolean enable;
	
	@OneToOne
	@JoinColumn(name = "parent_id")
	private Category categoryParent;
	
	@OneToMany(mappedBy = "categoryParent")
	@OrderBy("name asc")
	private Set<Category> children = new HashSet<>();
	
	
	@ManyToMany(mappedBy = "categories")
	private List<Brand> brands= new ArrayList<>();
	
	@ManyToMany(mappedBy = "listCategory")
	private List<Product> listProduct= new ArrayList<>();

	public Category() {
		super();
	}



	public Category(String name, String alias, String image, boolean enable, Category categoryParent,
			HashSet<Category> children) {
		super();
		this.name = name;
		this.alias = alias;
		this.image = image;
		this.enable = enable;
		this.categoryParent = categoryParent;
		this.children = children;
	}



	public Category(Integer id, String name, String alias, String image, boolean enable, Category categoryParent,
			HashSet<Category> children) {
		super();
		this.id = id;
		this.name = name;
		this.alias = alias;
		this.image = image;
		this.enable = enable;
		this.categoryParent = categoryParent;
		this.children = children;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getAlias() {
		return alias;
	}



	public void setAlias(String alias) {
		this.alias = alias;
	}



	public String getImage() {
		return image;
	}



	public void setImage(String image) {
		this.image = image;
	}



	public boolean isEnable() {
		return enable;
	}



	public void setEnable(boolean enable) {
		this.enable = enable;
	}



	public Category getCategoryParent() {
		return categoryParent;
	}



	public void setCategoryParent(Category categoryParent) {
		this.categoryParent = categoryParent;
	}



	public Set<Category> getChildren() {
		return children;
	}



	public void setChildren(HashSet<Category> children) {
		this.children = children;
	}



	public Category(Integer id, String name, String alias) {
		super();
		this.id = id;
		this.name = name;
		this.alias = alias;
	}
	
	@Transient
	public String getPathImage() {
		if( this.image == null || this.image.equals("")) {
			return "/image/imgDefault.png";	
			}
		return "/category-photos/"+this.id+"/"+this.image;
	}



	public List<Brand> getBrands() {
		return brands;
	}



	public void setBrands(List<Brand> brands) {
		this.brands = brands;
	}



	public void setChildren(Set<Category> children) {
		this.children = children;
	}



	public Category(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	

}
