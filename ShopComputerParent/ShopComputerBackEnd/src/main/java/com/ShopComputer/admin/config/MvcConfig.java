package com.ShopComputer.admin.config;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer{

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String dirUser= "user-photos";
		Path userPhotoDir = Paths.get(dirUser);
		String userPhotoPath = userPhotoDir.toFile().getAbsolutePath();
		registry.addResourceHandler("/"+dirUser+"/**")
		.addResourceLocations("file:/"+userPhotoPath+"/");
		
		String dirBrand="../brand-photos";
		Path brandPhotoDir = Paths.get(dirBrand);
		String brandPhotoPath = brandPhotoDir.toFile().getAbsolutePath();
		registry.addResourceHandler("/brand-photos"+"/**")
		.addResourceLocations("file:/"+brandPhotoPath+"/");
		;
		
	}
	
	
	

}
