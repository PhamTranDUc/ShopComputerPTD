package com.ShopComputer.EntityCommon;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class ProductImage {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	public ProductImage() {
		super();
	}

	public ProductImage(String name, Product product) {
		super();
		this.name = name;
		this.product = product;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	@Transient
	public String getPathImageExtra() {
		if(this.name == null || this.name.equals("")) {
			return "/image/imgDefault.png";
		}
		return "/product-photos/"+this.product.getId()+"/extras/"+this.name;
	}
}
