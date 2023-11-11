package com.ShopComputer.admin.setting;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ShopComputer.EntityCommon.Setting;
import com.ShopComputer.EntityCommon.SettingCategory;

@Service
public class SettingService {
	
	@Autowired
	private SettingRepository settingRepository;
	
	public List<Setting> getAllSetting(){
		return (List<Setting>) settingRepository.findAll();
	}
	
	public SettingBag getGeneralSetting(){
		List<Setting> listRs= new ArrayList<>();		
		List<Setting> settingCurrency= settingRepository.findBySettingCategory(SettingCategory.CURRENTCY);
		List<Setting> settingGeneral= settingRepository.findBySettingCategory(SettingCategory.GENERAL);
		listRs.addAll(settingCurrency);
		listRs.addAll(settingGeneral);
		return new SettingBag(listRs);
	}
	
	
	public void saveAll(Iterable<Setting> listSetting) {
		settingRepository.saveAll(listSetting);
	}

}
