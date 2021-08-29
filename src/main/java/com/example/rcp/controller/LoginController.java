package com.example.rcp.controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.rcp.domain.Members;
import com.example.rcp.service.LoginService;


import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
public class LoginController {
	
	@Autowired
	LoginService loginService;
	

	@GetMapping("/")
	public String loginForm(Model model) {
		model.addAttribute("member", new Members());
		return "loginForm";
	}
	
	@PostMapping("/login")
	public String login(@ModelAttribute("member") Members member,BindingResult bindingResult) throws Exception {
		
		log.info("memberEmail={}", member.getMemberEmail());
		
		Members loginMember = loginService.login(member.getMemberEmail(),member.getMemberPassword());
		
		if(loginMember == null) {
			//bindingResult.reject("loginFail", "incorrect email or password");
		bindingResult.addError(new ObjectError("member","Incorrect email of password!!"));
		
		if(bindingResult.hasErrors()){
			log.info("errors={}",bindingResult);
					
			return "loginForm";
		}
		
					 
		}
		
		return "home";
		
		
	}
	

	

}
