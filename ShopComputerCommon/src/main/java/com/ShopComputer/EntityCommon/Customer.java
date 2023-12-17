package com.ShopComputer.EntityCommon;



import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	
	public String firstName;
	
	public String lastName;
	
	public String email;
	
	public String phoneNumber;
	
	public String address1;
	
	public String address2;
	
	public String passWord;
	
	public Date createTime;
	
	public boolean enable;
	
	public String verificationCode;
	
	public AuthenticationType authenticationType;
	

	public Customer() {
	}



	public Customer(Long id, String firstName, String lastName, String email, String phoneNumber, String address1,
			String address2, String passWord, Date createTime, boolean enable, String verificationCode) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address1 = address1;
		this.address2 = address2;
		this.passWord = passWord;
		this.createTime = createTime;
		this.enable = enable;
		this.verificationCode = verificationCode;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPhoneNumber() {
		return phoneNumber;
	}



	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}



	public String getAddress1() {
		return address1;
	}



	public void setAddress1(String address1) {
		this.address1 = address1;
	}



	public String getAddress2() {
		return address2;
	}



	public void setAddress2(String address2) {
		this.address2 = address2;
	}



	public String getPassWord() {
		return passWord;
	}



	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}



	public Date getCreateTime() {
		return createTime;
	}



	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}



	public boolean getEnable() {
		return enable;
	}



	public void setEnable(boolean enable) {
		this.enable = enable;
	}



	public String getVerificationCode() {
		return verificationCode;
	}



	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}



	public Customer(String firstName, String lastName, String email, String phoneNumber, String address1,
			String address2, String passWord, Date createTime, boolean enable, String verificationCode) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address1 = address1;
		this.address2 = address2;
		this.passWord = passWord;
		this.createTime = createTime;
		this.enable = enable;
		this.verificationCode = verificationCode;
	}



	public Customer(String firstName, String lastName, String email, String phoneNumber, String address1,
			String passWord, Date createTime, boolean enable, String verificationCode) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address1 = address1;
		this.passWord = passWord;
		this.createTime = createTime;
		this.enable = enable;
		this.verificationCode = verificationCode;
	}



	public AuthenticationType getAuthenticationType() {
		return authenticationType;
	}



	public void setAuthenticationType(AuthenticationType authenticationType) {
		this.authenticationType = authenticationType;
	}
	
	
	
	
	

}
