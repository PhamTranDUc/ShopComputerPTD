package com.ShopComputer.EntityCommon;

import java.util.List;

import com.ShopComputer.EntityCommon.Setting;

public class SettingBag {
	
	public List<Setting> listSetting;
	
	public SettingBag(List<Setting> listSettings) {
		this.listSetting= listSettings;
	}
	
	public Setting get(String key) {
		Integer index= listSetting.indexOf(new Setting(key));
		if(index>=0) {
			return listSetting.get(index);
		}
		return null;
	}
	
	public String getValue(String key) {
		for(Setting s: listSetting) {
			if(s.getSettingKey().equals(key)) {
				return s.getSettingValue();
			}
		}
		return null;
	}
	
	public void update(String key, String value) {
		Setting setting = this.get(key);
		if(setting != null) {
			if(value != null && !value.equals("")) {
			setting.setSettingValue(value);	
			}
		}
	}
	
	public List<Setting> listSettings(){
		return this.listSetting;
	}

}
