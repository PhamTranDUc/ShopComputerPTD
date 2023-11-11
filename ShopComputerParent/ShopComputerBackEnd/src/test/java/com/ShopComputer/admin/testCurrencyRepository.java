package com.ShopComputer.admin;

import com.ShopComputer.EntityCommon.Currency;
import com.ShopComputer.EntityCommon.Setting;
import com.ShopComputer.admin.setting.CurrencyRepository;
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
public class testCurrencyRepository {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Test
    public void addCurrency(){
        Currency c1= new Currency("United States Dollar","$","USD");
        Currency c2= new Currency("Vietnamese Đồng","đ","VND");
        Currency c3= new Currency("Japanese Yen","¥","JPY");
        Currency c4= new Currency("Euro","€","EUR");
        Currency c5= new Currency("Russian Ruble","₽","RUB");
        Currency c6= new Currency("South Korean Won","₩","KRW");
        Currency c7= new Currency("Chinese Yuan","¥","CNY");
        Currency c8= new Currency("Indian Rupee","₹","INR");
        Iterable<Currency> listCurrency =currencyRepository.saveAll(List.of(c1,c2,c3,c4,c5,c6,c7,c8));
        int count=0;
        for(Currency c: listCurrency){
            count++;
        }
        Assertions.assertEquals(count,8);

    }

    @Test
    public void findAllByOrderByNameAsc(){
        List<Currency> listRs= currencyRepository.findAllByOrderByNameAsc();
        for(Currency c: listRs){
            System.out.println(c);
        }
    }
}
