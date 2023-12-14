package com.ShopComputer.site.customer;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ShopComputer.EntityCommon.Customer;
import com.ShopComputer.EntityCommon.EmailSettingBag;
import com.ShopComputer.site.Utility;
import com.ShopComputer.site.setting.SettingService;

@Controller
public class CustomerController {
	
	@Autowired
	public CustomerService customerService;
	@Autowired
	public SettingService settingService;
	
	@GetMapping("/register")
	public String getFormRegister(Model model) {
		Customer customer= new Customer();
		model.addAttribute("customer", customer);
		return "/customer_register/register_form";
	}
	@PostMapping("/register")
	public String registerCustomer(Customer customer,Model model,HttpServletRequest request) throws UnsupportedEncodingException, MessagingException {
		customerService.registerCustomer(customer);
		sendVerificationEmail(request,customer);
		return "/customer_register/register_success";
		
	}
	private void sendVerificationEmail(HttpServletRequest request, Customer customer) throws UnsupportedEncodingException, MessagingException {
		EmailSettingBag emailSetting= settingService.getEmailSettings();
		JavaMailSenderImpl mailSender= Utility.prepareMailSender(emailSetting);
		
		
		String toAddress = customer.getEmail();
		String subject= emailSetting.getCUSTOMER_VERIFY_SUBJECT();
		String content= emailSetting.getCUSTOMER_VERIFY_CONTENT();
		
		MimeMessage message = mailSender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(message);
	    
	    helper.setFrom(emailSetting.getMailFrom(), emailSetting.getMAIL_SENDER_NAME());
	    helper.setTo(toAddress);
	    helper.setSubject(subject);
	    
	    content= content.replace("[[name]]", customer.getFirstName()+" "+customer.getLastName());
	    String verifyURL= Utility.getSiteUrl(request)+"/verify?code="+customer.getVerificationCode();
	    content = content.replace("URL", verifyURL);
	    
	    helper.setText(content,true);
	    mailSender.send(message);
	}
	

}
