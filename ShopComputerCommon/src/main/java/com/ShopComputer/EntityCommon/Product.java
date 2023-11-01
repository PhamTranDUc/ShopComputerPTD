package com.ShopComputer.EntityCommon;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 255, nullable = false, unique= true)
	private String name;
	
	@Column(length = 255, nullable = false, unique= true)
	private String alias;
	
	@Column(length = 512)
	private String shortDescription;
	
	@Column(length = 4096)
	private String fullDescription;
	
	
	private double cost;
	
	private double discountPercent;
	
	private double price;
	
	@ManyToMany()
	@JoinTable(
			name = "products_categories",
			joinColumns = @JoinColumn(name = "product_id"),
			inverseJoinColumns = @JoinColumn(name = "category_id")
			)
	private List<Category> listCategory;
	
	@ManyToOne
	@JoinColumn(name = "brand_id")
	private Brand brand;
	
	@OneToMany(mappedBy = "product",orphanRemoval = true)
	private List<ProductDetail> productDetails;
	
	@OneToMany(mappedBy = "product",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<ProductImage> productImages= new ArrayList<>();
	
	private boolean enable;
	
	private boolean inStock;
	
	private double weight;
	
	private double height;
	
	private double length;
	
	private Date createTime;
	
	private Date updateTime;
	
	private String mainImage;

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

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getFullDescription() {
		return fullDescription;
	}

	public void setFullDescription(String fullDescription) {
		this.fullDescription = fullDescription;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public double getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(double discountPercent) {
		this.discountPercent = discountPercent;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public List<Category> getListCategory() {
		return listCategory;
	}

	public void setListCategory(List<Category> listCategory) {
		this.listCategory = listCategory;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public boolean isInStock() {
		return inStock;
	}

	public void setInStock(boolean inStock) {
		this.inStock = inStock;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getMainImage() {
		return mainImage;
	}

	public void setMainImage(String mainImage) {
		this.mainImage = mainImage;
	}
	

	public Product() {
		super();
	}

	public Product(String name, String alias, String shortDescription, String fullDescription, double cost,
			double discountPercent, double price, List<Category> listCategory, Brand brand, boolean enable,
			boolean inStock, double weight, double height, double length, Date createTime, Date updateTime,
			String mainImage) {
		super();
		this.name = name;
		this.alias = alias;
		this.shortDescription = shortDescription;
		this.fullDescription = fullDescription;
		this.cost = cost;
		this.discountPercent = discountPercent;
		this.price = price;
		this.listCategory = listCategory;
		this.brand = brand;
		this.enable = enable;
		this.inStock = inStock;
		this.weight = weight;
		this.height = height;
		this.length = length;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.mainImage = mainImage;
	}

	public Product(Long id, String name, String alias, String shortDescription, String fullDescription, double cost,
			double discountPercent, double price, List<Category> listCategory, Brand brand, boolean enable,
			boolean inStock, double weight, double height, double length, Date createTime, Date updateTime,
			String mainImage) {
		super();
		this.id = id;
		this.name = name;
		this.alias = alias;
		this.shortDescription = shortDescription;
		this.fullDescription = fullDescription;
		this.cost = cost;
		this.discountPercent = discountPercent;
		this.price = price;
		this.listCategory = listCategory;
		this.brand = brand;
		this.enable = enable;
		this.inStock = inStock;
		this.weight = weight;
		this.height = height;
		this.length = length;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.mainImage = mainImage;
	}

	@Transient
	public String getShortName() {
		String rs= this.getName();
		if(rs.length()> 25) {
			String tmp= rs.substring(0, 25).concat("...");
			return tmp;
		}
		return rs;
	}
	
	@Transient
	public String getShortAlias() {
		String rs= this.getAlias();
		if(rs.length()> 25) {
			String tmp= rs.substring(0, 25).concat("...");
			return tmp;
		}
		return rs;
	}

	public List<ProductDetail> getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(List<ProductDetail> productDetails) {
		this.productDetails = productDetails;
	}

	public List<ProductImage> getProductImages() {
		return productImages;
	}

	public void setProductImages(List<ProductImage> productImages) {
		this.productImages = productImages;
	}

	public void addProductImageExtra(String nameImg){
		this.productImages.add(new ProductImage(nameImg,this));
	}
	
	@Transient
	public String getPathImageMain() {
		if(this.mainImage== null || this.mainImage.equals("")) {
			return "/image/imgDefault.png";	
		}
		return "/product-photos/"+this.id+"/"+this.mainImage;
	}
}
