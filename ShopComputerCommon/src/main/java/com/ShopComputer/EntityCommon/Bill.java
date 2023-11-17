package com.ShopComputer.EntityCommon;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Bill {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(mappedBy = "bill")
	private List<CartItem> CartItem;
	
	private String address;
	
	private String note;

	@Enumerated(EnumType.STRING)
	private BillStatus status;
	
	public Bill() {
		super();
	}

	public Bill(Long id, List<CartItem> cartItem, String address, String note) {
		super();
		this.id = id;
		CartItem = cartItem;
		this.address = address;
		this.note = note;
	}

	public Bill(List<CartItem> cartItem, String address, String note) {
		super();
		CartItem = cartItem;
		this.address = address;
		this.note = note;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<CartItem> getCartItem() {
		return CartItem;
	}

	public void setCartItem(List<CartItem> cartItem) {
		CartItem = cartItem;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public BillStatus getStatus() {
		return status;
	}

	public void setStatus(BillStatus status) {
		this.status = status;
	}
	
	
	

}
