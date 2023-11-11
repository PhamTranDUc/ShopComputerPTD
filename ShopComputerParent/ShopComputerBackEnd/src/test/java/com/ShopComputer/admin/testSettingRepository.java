package com.ShopComputer.admin;

import com.ShopComputer.EntityCommon.Setting;
import com.ShopComputer.EntityCommon.SettingCategory;
import com.ShopComputer.admin.setting.SettingRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;


import java.util.List;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class testSettingRepository {

    @Autowired
    private SettingRepository settingRepository;


    @Test
    public void testCreateGeneralSettings(){
        Setting siteName= new Setting(SettingCategory.GENERAL,"SITE_NAME","ShopComputerPTD");
        Setting siteLogo= new Setting(SettingCategory.GENERAL,"SITE_LOGO","Shopme.png");
        Setting copyright = new Setting(SettingCategory.GENERAL,"COPYRIGHT","Copyright (C) 2023 ShopComputerPTD");
        settingRepository.saveAll(List.of(siteName,siteLogo,copyright));

        Iterable<Setting> listSetting= settingRepository.findAll();
        int count=0;
        for(Setting s: listSetting){
            count++;
        }
        Assertions.assertNotEquals(count,0);
    }

    @Test
    public void testCreateCurrencySettings(){
        Setting currencyId= new Setting(SettingCategory.CURRENTCY,"CURRENCY_ID","1");
        Setting symbol= new Setting(SettingCategory.CURRENTCY,"CURRENCY_SYMBOL","$");
        Setting symbolPosition= new Setting(SettingCategory.CURRENTCY,"CURRENCY_SYMBOL_POSITION","before");
        Setting decimalPointType= new Setting(SettingCategory.CURRENTCY,"DECIMAL_POINT_TYPE","POINT");
        Setting decimalDigits= new Setting(SettingCategory.CURRENTCY,"DECIMAL_DIGITS","2");
        Setting thousandsPointType= new Setting(SettingCategory.CURRENTCY,"THOUSANDS_POINT_TYPE","COMMA");

        Iterable<Setting> listSave=settingRepository.saveAll(List.of(currencyId,symbol,symbolPosition,decimalPointType,decimalDigits,thousandsPointType));
        Assertions.assertNotEquals(listSave,null);

    }


    @Test
    public void testFindByCategory(){
        List<Setting> listSettingCurrency= settingRepository.findBySettingCategory(SettingCategory.CURRENTCY);
        for(Setting s: listSettingCurrency){
            System.out.println(s);
        }

        System.out.println("///////");
        List<Setting> listSettingGeneral= settingRepository.findBySettingCategory(SettingCategory.GENERAL);
        for(Setting s: listSettingGeneral){
            System.out.println(s);
        }
    }
}
