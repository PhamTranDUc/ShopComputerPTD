package com.ShopComputer.site;

import java.net.http.HttpRequest;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.ShopComputer.EntityCommon.EmailSettingBag;

public class Utility {
	public static String getSiteUrl(HttpServletRequest request) {
		String siteURL = request.getRequestURL().toString();
		return siteURL.replace(request.getServletPath(),"");
	}
	
	public static JavaMailSenderImpl prepareMailSender(EmailSettingBag settings) {
		JavaMailSenderImpl mailSender= new JavaMailSenderImpl();
		mailSender.setHost(settings.getHost());
		mailSender.setPort(settings.getPost());
		
		mailSender.setUsername(settings.getUserName());
		mailSender.setPassword(settings.getPassword());
		
		Properties mailProperties = new Properties();
		mailProperties.setProperty("mail.smtp.auth", settings.getSMTP_AUTH());
		mailProperties.setProperty("mail.smtp.starttls.enable", settings.getSMTP_SECURED());
		mailSender.setJavaMailProperties(mailProperties);
		return mailSender;
	}

}
