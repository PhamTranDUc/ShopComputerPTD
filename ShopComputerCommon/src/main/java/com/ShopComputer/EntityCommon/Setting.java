package com.ShopComputer.EntityCommon;

import java.util.Objects;

import javax.persistence.*;

@Entity
public class Setting {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Override
	public String toString() {
		return "Setting [settingCategory=" + settingCategory + ", settingKey=" + settingKey + ", settingValue="
				+ settingValue + "]";
	}

	@Enumerated(EnumType.STRING)
	private SettingCategory settingCategory;
	
	@Column(nullable = false,length = 45)
	private String settingKey;
	
	@Column(nullable = false,length = 45)
	private String settingValue;

	public Setting(SettingCategory settingCategory, String settingKey, String settingValue) {
		super();
		this.settingCategory = settingCategory;
		this.settingKey = settingKey;
		this.settingValue = settingValue;
	}


	public Setting(String settingKey) {
		super();
		this.settingKey = settingKey;
	}


	public Setting() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public SettingCategory getSettingCategory() {
		return settingCategory;
	}

	public void setSettingCategory(SettingCategory settingCategory) {
		this.settingCategory = settingCategory;
	}

	public String getSettingKey() {
		return settingKey;
	}

	public void setSettingKey(String settingKey) {
		this.settingKey = settingKey;
	}







	public String getSettingValue() {
		return settingValue;
	}

	public void setSettingValue(String settingValue) {
		this.settingValue = settingValue;
	}


	@Override
	public int hashCode() {
		return Objects.hash(settingKey);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Setting other = (Setting) obj;
		return Objects.equals(settingKey, other.settingKey);
	}
	
	

}
