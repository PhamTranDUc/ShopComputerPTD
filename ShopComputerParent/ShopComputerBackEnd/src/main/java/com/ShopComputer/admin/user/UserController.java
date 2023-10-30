package com.ShopComputer.admin.user;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ShopComputer.EntityCommon.User;
import com.ShopComputer.admin.FileUploadUtil;
import com.ShopComputer.admin.role.RoleService;


@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@GetMapping("/users")
	public String getAllUser(Model model) {
		return getUserByPage(1, "id","asc", model);
	}
	
	@GetMapping("/users/page/{currentPage}")
	public String getUserByPage(@PathVariable("currentPage") int currentPage,@Param("sortBy")String sortBy,@Param("sortType") String sortType,Model model) {
		Page<User> pageRs= userService.getByPage(sortBy, sortType, currentPage, null);
		String sortRever=sortType.equals("asc")?"desc":"asc";
		model.addAttribute("listUser", userService.getByPage(sortBy, sortType, currentPage, null));
		model.addAttribute("numberUser", pageRs.getTotalElements());
		model.addAttribute("sortType",sortType);
		model.addAttribute("sortBy", sortBy);
		model.addAttribute("sortRever", sortRever);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPage", pageRs.getTotalPages());
		return "users/users";
	}
	
	@GetMapping("/users/{id}")
	public String setEnable(@PathVariable("id") Long id,@Param("enable") boolean enable,RedirectAttributes redirectAttributes,Model model) {
		boolean check=(enable == true)?false:true;
		userService.setEnable(id, check);
		String tmpMessage=(enable == true)?"disable":"enable";
		redirectAttributes.addFlashAttribute("message",tmpMessage+" user id = "+id+" success !");
		return "redirect:/users";
	}
	
	@GetMapping("users/detail/{id}")
	public String getDetailUser(@PathVariable("id") Long id,Model model) {
		User user= userService.findById(id);
		model.addAttribute("user", user);
		model.addAttribute("listRole", roleService.getAll());
		return "users/user_detail";
	}
	
	@PostMapping("/users/save")
	public String saveUser(User user,RedirectAttributes redirectAttributes,@RequestParam("photo") MultipartFile multipartFile) throws IOException {
		String email= user.getEmail();
		User userTmp = userService.findByEmail(email);
		String passWord= user.getPassword();
		if(passWord.equals("")) {
			user.setPassword(userTmp.getPassword());
		}else {	
				user.setPassword(userService.encodePassword(passWord));
		}
		User userSave= userService.saveUser(user);
		if(!multipartFile.isEmpty()) {
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			user.setImage(fileName);
			String uploadDir= "user-photos/"+userSave.getId();
			FileUploadUtil.cleanDir(uploadDir);
			FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);			
		}
		if(userTmp == null) {
			redirectAttributes.addFlashAttribute("message","Tài khoản thành viên đã được lưu lại thành công !");
		}else {
			userService.saveUser(user);
			redirectAttributes.addFlashAttribute("message","Tài khoản có id = "+userTmp.getId()+" đã được cập nhập lại thành công !");
		}
		return "redirect:/users";
	}
	@GetMapping("/users/delete/{id}")
	public String deleteUser(@PathVariable("id") Long id,RedirectAttributes redirectAttributes) {
		userService.deleteUserById(id);
		redirectAttributes.addFlashAttribute("message","Xoá tài khoản User có Id = "+id+" thành công !");
		return "redirect:/users";
	}
	
	@GetMapping("/users/formUser")
	public String getForm(Model model) {
		User user= new User();
		model.addAttribute("user", user);
		model.addAttribute("listRole", roleService.getAll());
		return "users/user_form";
	}
	
	@GetMapping("/users/getFormEdit/{id}")
	public String getFormEdit(@PathVariable("id")Long id, Model model) {
		User user= userService.findById(id);
		model.addAttribute("user", user);
		model.addAttribute("listRole", roleService.getAll());
		return "users/user_form";
	}

}
