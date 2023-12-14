package com.ShopComputer.admin.setting;

import java.util.List;

import com.ShopComputer.EntityCommon.Setting;
import com.ShopComputer.EntityCommon.SettingBag;

public class SettingBagGeneral extends SettingBag{

	public SettingBagGeneral(List<Setting> listSettings) {
		super(listSettings);
		// TODO Auto-generated constructor stub
	}
	
	public void updateSiteLogo(String value) {
		super.update("SITE_LOGO", value);
	}
	
	public void updateCurrencySymbol(String value) {
		super.update("CURRENCY_SYMBOL", value);
	}

}
