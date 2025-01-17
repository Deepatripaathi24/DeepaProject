package com.myApp.controller;

import com.myApp.Dto.UserDTO;
import com.myApp.dao.UserDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
	
	@Autowired
	private PasswordEncoder bcryptPasswordEncoder;
	
	@Autowired
	private UserDAO userDAO;
	
	@GetMapping("/myLoginPage")
	public String loginHere()
	{
		return "login-page";
	}

	@GetMapping("/userRegistration")
	public String registerHere(@ModelAttribute("myRegister") UserDTO userDTO)
	{
		return "register";
	}
	
	
	@PostMapping("/process-registration")
	public String processRegistration(UserDTO userDTO)
	{
		System.out.println(userDTO);
		
	String password =	bcryptPasswordEncoder.encode(userDTO.getPassword());
	userDTO.setPassword(password);
	
		//logic to save in DB		
		userDAO.SaveUser(userDTO);		
		
	
		
		return "redirect:/myLoginPage";
	}
}
