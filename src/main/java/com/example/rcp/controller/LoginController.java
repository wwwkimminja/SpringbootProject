package com.example.rcp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.example.rcp.domain.LoginMember;

import com.example.rcp.mapper.MembersMapper;
import com.example.rcp.service.LoginService;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	MembersMapper membersMapper;
	

	
	@GetMapping("/")
	public String homeLogin(@SessionAttribute(name="loginMember",required=false) LoginMember loginMember, Model model) throws Exception {

		
		
		if(loginMember == null) {
			return "home";
		}
		
		model.addAttribute("loginMember",loginMember);
		return "loginHome";
	}
	
	

	@GetMapping("/login")
	public String loginForm(@ModelAttribute("loginMember") LoginMember lgoinMember) {
		return "loginForm";
	}
	
	
	
	@PostMapping("/login")
	public String login(@ModelAttribute("loginMember") LoginMember loginMember,BindingResult bindingResult, HttpServletRequest request) throws Exception {
		
		
		loginMember = loginService.login(loginMember.getMemberEmail(),loginMember.getMemberPassword());
		
		//ログイン失敗
		if(loginMember == null) {
			bindingResult.reject("loginFail");

			return "loginForm";
		}
		
		//ログイン成功TODO 			
		
		HttpSession session = request.getSession();
		
		session.setAttribute("loginMember", loginMember);
		
		
		return "redirect:/";
		
		
	}
	
	@PostMapping("/logout")
	public String logout(HttpServletRequest request) {

		HttpSession session = request.getSession(false);
		if(session != null) {
			session.invalidate();
			
		}
		
		
		return "redirect:/";
	}
	

	

}
