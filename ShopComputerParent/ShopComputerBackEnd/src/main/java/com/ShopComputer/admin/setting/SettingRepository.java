package com.ShopComputer.admin.setting;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ShopComputer.EntityCommon.Setting;
import com.ShopComputer.EntityCommon.SettingCategory;

@Repository
public interface SettingRepository extends CrudRepository<Setting, Integer>{
	public  List<Setting> findBySettingCategory(SettingCategory settingCategory);

}
