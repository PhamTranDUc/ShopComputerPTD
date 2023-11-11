package com.ShopComputer.admin.setting;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ShopComputer.EntityCommon.Currency;

@Repository
public interface CurrencyRepository extends CrudRepository<Currency,Long>{

	public List<Currency> findAllByOrderByNameAsc();
}
