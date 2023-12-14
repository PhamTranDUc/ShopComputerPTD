package com.ShopComputer.EntityCommon;

import java.util.List;

import com.ShopComputer.EntityCommon.Setting;
import com.ShopComputer.EntityCommon.SettingBag;

public class EmailSettingBag extends SettingBag{

	public EmailSettingBag(List<Setting> listSettings) {
		super(listSettings);
	}
	public String getHost() {
		return super.getValue("MAIL_HOST");
	}
	
	public Integer getPost() {
		return Integer.parseInt(super.getValue("MAIL_POST"));
	}
	
	public String getUserName() {
		return super.getValue("MAIL_USERNAME");
	}
	
	public String getPassword() {
		return super.getValue("MAIL_PASSWORD");
	}
	
	public String getMailFrom() {
		return super.getValue("MAIL_FROM");
	}
	public String getSMTP_AUTH() {
		return super.getValue("SMTP_AUTH");
	}
	public String getSMTP_SECURED() {
		return super.getValue("SMTP_SECURED");
	}
	public String getMAIL_SENDER_NAME() {
		return super.getValue("MAIL_SENDER_NAME");
	}
	public String getCUSTOMER_VERIFY_CONTENT() {
		return super.getValue("CUSTOMER_VERIFY_CONTENT");
	}
	public String getCUSTOMER_VERIFY_SUBJECT() {
		return super.getValue("CUSTOMER_VERIFY_SUBJECT");
	}

}
