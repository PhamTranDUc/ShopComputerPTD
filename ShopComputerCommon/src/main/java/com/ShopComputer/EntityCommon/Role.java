package com.ShopComputer.EntityCommon;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false,unique = true)
	private String name;
	
	@Column(nullable = false)
	private String description;
	
	@ManyToMany(mappedBy = "roles")
	private List<User> listUser = new ArrayList<>();
	
	

	public Role() {
		super();
	}


	public Role(String name, String description, List<User> listUser) {
		super();
		this.name = name;
		this.description = description;
		this.listUser = listUser;
	}



	public Role(Integer id, String name, String description, List<User> listUser) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.listUser = listUser;
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


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public List<User> getListUser() {
		return listUser;
	}


	public void setListUser(List<User> listUser) {
		this.listUser = listUser;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Role role = (Role) o;
		return Objects.equals(id, role.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
