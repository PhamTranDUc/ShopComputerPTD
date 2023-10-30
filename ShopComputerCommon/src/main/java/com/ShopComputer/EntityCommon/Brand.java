package com.ShopComputer.EntityCommon;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Brand {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	

	@Column(unique = true)
	private String name;
	
	private String image;
	
	@ManyToMany()
	@JoinTable(
			name= "brands_categories",
	joinColumns = @JoinColumn(name = "brand_id"),
	inverseJoinColumns = @JoinColumn(name="category_id")
    )
	private List<Category> categories = new ArrayList<>();

	public Brand() {
		super();
	}

	public Brand(String name, String image) {
		super();
		this.name = name;
		this.image = image;
	}
	
	public Brand(String name) {
		super();
		this.name = name;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	@Transient
	public String getPathImage() {
		if( this.image == null || this.image.equals("")) {
			return "/image/imgDefault.png";	
			}
		return "/brand-photos/"+this.id+"/"+this.image;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	

}
