package com.ShopComputer.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.ShopComputer.EntityCommon"})
public class ShopComputerBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopComputerBackEndApplication.class, args);
	}

}
