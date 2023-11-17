package com.ShopComputer.EntityCommon;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class CartItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;
	
	@OneToOne
	@JoinColumn(name="product_id")
	private Product product;
	
	@OneToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	private int quantity;
	
	@ManyToOne
	@JoinColumn(name="bill_id")
	private Bill bill;

	public CartItem() {
		super();
	}

	public CartItem(Long id, Product product, Customer customer, int quantity) {
		super();
		this.id = id;
		this.product = product;
		this.customer = customer;
		this.quantity = quantity;
	}

	public CartItem(Product product, Customer customer, int quantity) {
		super();
		this.product = product;
		this.customer = customer;
		this.quantity = quantity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "CartItem [id=" + id + ", product=" + product + ", customer=" + customer + ", quantity=" + quantity
				+ ", getId()=" + getId() + ", getProduct()=" + getProduct() + ", getCustomer()=" + getCustomer()
				+ ", getQuantity()=" + getQuantity() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}
	
	

}
