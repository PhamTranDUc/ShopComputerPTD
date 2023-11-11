package com.ShopComputer.admin.setting;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ShopComputer.EntityCommon.Currency;
import com.ShopComputer.EntityCommon.Setting;
import com.ShopComputer.admin.FileUploadUtil;

@Controller
public class SettingController {
	
	@Autowired
	private SettingService settingService;
	
	@Autowired
	private CurrencyRepository currencyRepository;
	
	@GetMapping("/settings")
	public String getSettings(Model model) {
		List<Setting> listSetting= settingService.getAllSetting();
		List<Currency> listCurrencies = currencyRepository.findAllByOrderByNameAsc();
		model.addAttribute("listCurrency", listCurrencies);
		for(Setting s: listSetting) {
			model.addAttribute(s.getSettingKey(), s.getSettingValue());
		}
		return "settings/settings";
	}
	
	@PostMapping("/settings/save_general")
	public String saveSettingGeneral(RedirectAttributes rd,HttpServletRequest http,@RequestParam("fileImg")MultipartFile multipartFile) throws IOException {
		List<Setting> listSetting= settingService.getAllSetting();
		SettingBagGeneral settingBagGeneral= new SettingBagGeneral(listSetting);
		saveSiteLogo(multipartFile,settingBagGeneral);
		saveCurrencySymbol(http.getParameter("CURRENCY_ID"),settingBagGeneral);
		saveSettingGeneralForForm(http,settingBagGeneral.listSetting);
        rd.addFlashAttribute("message", "Cài đặt của bạn đã được lưu lại!");
		return "redirect:/settings";
	}
	
	public void saveSiteLogo(MultipartFile multipartFile,SettingBagGeneral settingBag) throws IOException {
		if(multipartFile!=null||!multipartFile.isEmpty()) {
		     String name=StringUtils.cleanPath(multipartFile.getOriginalFilename());
		     String value= "/site-logo/"+name;
		     String uploadDir= "../site-logo";
		     FileUploadUtil.cleanDir(value);
		     FileUploadUtil.saveFile(uploadDir, name, multipartFile);		     
		}
	}
	
	public void saveCurrencySymbol(String id, SettingBagGeneral settingBagGeneral) {
		Long idCurrency= Long.parseLong(id);
		Optional<Currency> currencyRs=currencyRepository.findById(idCurrency);
		if(currencyRs.isPresent()) {
			Currency currency= currencyRs.get();
			String tmp=currency.getSymbol();
			settingBagGeneral.updateCurrencySymbol(currency.getSymbol());
		}
	}
	
	public void saveSettingGeneralForForm(HttpServletRequest http,List<Setting> listSetting) {
		for(Setting s: listSetting) {
			String value= http.getParameter(s.getSettingKey());
			if(value!= null) {
				s.setSettingValue(value);
			}
		}
		settingService.saveAll(listSetting);
	}

}
